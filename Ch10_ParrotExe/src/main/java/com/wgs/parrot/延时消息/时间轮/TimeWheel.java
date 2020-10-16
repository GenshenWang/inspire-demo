package com.wgs.parrot.延时消息.时间轮;

import lombok.Data;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TimeWheel {

    private static final Logger logger = LoggerFactory.getLogger(TimeWheel.class);

    private static final int DEFAULT_BUFFER_SIZE = 8;
    private int bufferSize;
    private Object[] bufferData;

    /**
     * The real tasks in ring buffer
     */
    private final AtomicInteger taskSize = new AtomicInteger(0);
    private final AtomicBoolean start = new AtomicBoolean(false);
    private final AtomicBoolean stop = new AtomicBoolean(false);

    // executed count of tasks
    private AtomicInteger tick = new AtomicInteger(0);
    private AtomicInteger cursorIndex = new AtomicInteger(0);


    private Lock lock = new ReentrantLock();

    /**
     * When main thread invoke stop(), it will invoke condition.await to make itself wait
     * until other threads which execute tasks all finished;
     * when the threads execute finished,
     *  it will invoke condition.signal to make main thread wake up and stop.
     */
    private Condition condition = lock.newCondition();

    private ExecutorService executorService;

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public TimeWheel(ExecutorService executorService) {
        this.executorService = executorService;
        this.bufferSize = DEFAULT_BUFFER_SIZE;
        this.bufferData = new Object[this.bufferSize];
    }

    public TimeWheel(ExecutorService executorService, int bufferSize) {
        if (bufferSize <= 0) {
            throw new IllegalArgumentException("Invalid timeWeel bufferSize " + bufferSize);
        }
        if (!powerOf2(bufferSize)) {
            bufferSize = tableSizeFor(bufferSize);
        }
        this.executorService = executorService;
        this.bufferSize = bufferSize;
        this.bufferData = new Object[bufferSize];
    }

    private int tableSizeFor(int length) {
        int n = length - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

    private boolean powerOf2(int bufferSize) {
        return bufferSize % 2 == 0;
    }

    /**
     * 添加任务
     *
     * @param task 待执行的任务
     * @return  返回时间轮大小
     */
    public int addTask(Task task) {
        int key = task.getKey();
        // 先取模，获取在时间轮的哪一个位置
        int indexOfRingBuffer = mod(key);
        task.setIndex(indexOfRingBuffer);
        Set<Task> tasks = (Set<Task>) bufferData[indexOfRingBuffer];
        if (tasks != null) {
            task.setCycleNo(calculateCycleNo(key));
            tasks.add(task);
        } else {
            tasks = new HashSet<>();

            task.setCycleNo(calculateCycleNo(key));
            tasks.add(task);
            put(indexOfRingBuffer, tasks);
        }

        // Submit task to ExecutorPool to execute.
        if (start.compareAndSet(false, true)) {
            startJob();
        }

        return taskSize.incrementAndGet();
    }

    public void stop(boolean force) {
        if (force) {
            logger.info("TimeWheel is force to stop...");
            stop.compareAndSet(false, true);
            executorService.shutdownNow();
        } else {
            logger.info("TimeWheel is start to stop...");

            lock.lock();
            try {
            	condition.await();
                stop.compareAndSet(false, true);
            } catch (InterruptedException e) {
                // ...
            } finally {
                lock.unlock();
            }

            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                    if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                        logger.error("Failed to stop executorService pool.");
                    }
                }
            } catch (Exception e) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }

            if (executorService.isShutdown()) {
                logger.info("The TimeWheel is shutdown success...");
            }


        }


    }

    private void startJob() {
        Thread thread = new Thread(() -> {
            // int index = 0;
            while (!stop.get()) {
                // execute task
                Set<Task> tasks = remove(cursorIndex.get());
                // cursor index move
                if (cursorIndex.incrementAndGet() > bufferSize - 1) {
                    //index = 0;
                    cursorIndex.set(0);
                }

                if (tasks.size() > 0) {
                    for (Task task : tasks) {
                        executorService.submit(task);
                    }
                    // statistic execute tasks
                    tick.incrementAndGet();
                }

                // job execute frequency
                sleep(TimeUnit.SECONDS, 1);
            }

            logger.info("Ring-Buffer-Job-Thread is stop.");
        });
        thread.setName("Ring-Buffer-Job-Thread");
        thread.start();
    }

    private void sleep(TimeUnit seconds, int i) {
        try {
            seconds.sleep(i);
        } catch (InterruptedException e) {
            // ..
        }
    }

    private Set<Task> remove(int key) {
        Set<Task> result = new HashSet<>();
        Set<Task> bufferTasks = (Set<Task>) bufferData[key];
        if (bufferTasks == null) {
            return result;
        }

        Set<Task> copy = new HashSet<>();
        for (Task task : bufferTasks) {
            // cycleNo = 0 means the task should execute immediately.
            if (task.getCycleNo() == 0) {
                result.add(task);

                // if remained task = 0, then notify other threads.
                size2Notify();
            } else {
                task.setCycleNo(task.getCycleNo() - 1);
                copy.add(task);
            }
        }

        bufferData[key] = copy;
        return result;
    }

    private void size2Notify() {
        lock.lock();
        try {
            int remainTaskSize = taskSize.decrementAndGet();
            if (remainTaskSize == 0) {
                condition.signal();
            }
        } finally {
            lock.unlock();
        }

    }

    private void put(int index, Set<Task> tasks) {
        bufferData[index] = tasks;
    }

    private int calculateCycleNo(int key) {
        return key / bufferSize;
    }

    private int mod(int key) {
        key += cursorIndex.get();
        return key & (bufferSize - 1);
    }


    /**
     * 添加的任务
     * index + cycleNo 确定唯一任务
     */
    @Data
    public abstract static class Task extends Thread {
        // 在时间轮中的位置
        private int index;
        // 在时间轮中的圈数
        private int cycleNo;
        // 延时时间
        private int key;

        @Override
        public void run() {
        }
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        TimeWheel timeWheel = new TimeWheel(executorService, 64);


        // 5s执行的任务
        /*TimeWheel.Task task = new Job2(1);
        task.setKey(5);
        timeWheel.addTask(task);
        System.out.println("Add Task1===" + DateTime.now());


        task = new Job2(2);
        task.setKey(10);
        timeWheel.addTask(task);
        System.out.println("Add Task2===" + DateTime.now());


        task = new Job(3);
        task.setKey(20);
        timeWheel.addTask(task);
        System.out.println("Add Task3===" + DateTime.now());

        task = new Job(4);
        task.setKey(40);
        timeWheel.addTask(task);
        System.out.println("Add Task4 time 40s===" + DateTime.now());*/

        TimeWheel.Task task = new OrderCancelJob(1000001);
        task.setKey(30);
        int res = timeWheel.addTask(task);
        System.out.println("Add Order cancel Task1 time 30s===>" + DateTime.now() + ", task size = " + res);

        task = new OrderCancelJob(1000002);
        task.setKey(20);
        int res2 = timeWheel.addTask(task);
        System.out.println("Add Order cancel Task2 time 20s===>" + DateTime.now() + ", task size = " + res2);

        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        task = new OrderCancelJob(1000003);
        task.setKey(10);
        int res3 = timeWheel.addTask(task);
        System.out.println("Add Order cancel Task3 time 10s===>" + DateTime.now() + ", task size = " + res3);

        // timeWheel.stop(false);
    }

    static class OrderCancelJob extends TimeWheel.Task {
        private long orderId;

        public OrderCancelJob(long orderId) {
            this.orderId = orderId;
        }

        @Override
        public void run() {
            System.out.println("订单id: " + orderId + " 30分钟后查询OrderMapper中的订单状态, " + DateTime.now());

            System.out.println(orderId % 2 == 0 ? "订单未支付，开始取消" : "订单已支付");
        }
    }

    static class Job extends TimeWheel.Task {
        private int num;

        public Job(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            System.out.println("Execute current job number is : " + num + ", " + DateTime.now());
        }
    }

    static class Job2 extends TimeWheel.Task {
        private int num;

        public Job2(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            System.out.println("Execute current job2 number is : " + num + ", " + DateTime.now());
        }
    }

}

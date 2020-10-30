package com.wgs.parrot.延时消息.时间轮_001.v2;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 时间轮_001 v2.0
 */
public class TcpConnectUtil {

    private static final Logger logger = LoggerFactory.getLogger(TcpConnectUtil.class);

    private static final int DEFAULT_BUFFER_SIZE = 64;

    private int bufferSize;
    private Object[] bufferData;
    private ExecutorService executorService;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * The index in RingTimingWheel, move one step per second.
     */
    private AtomicInteger ringBufferCursor = new AtomicInteger(0);

    /**
     * The job is start
     */
    private final AtomicBoolean start = new AtomicBoolean(false);

    /**
     *  The job is stop.
     */
    private final AtomicBoolean stop = new AtomicBoolean(false);


    /**
     * The count of tasks.
     */
    private AtomicLong addTasksCount = new AtomicLong(0);
    private AtomicLong executedTasksCount = new AtomicLong(0);

    private final Map<Long, Integer> map = new HashMap<>();


    public abstract static class Task extends Thread{
        // The index in timeWheel.
        private int index;
        // The delay time
        private int key;
        // The cycleNo in timeWheel.
        private int cycleNo;
        // The task is cancel.
        private boolean cancel;

        private long userId;

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public void setCycleNo(int cycleNo) {
            this.cycleNo = cycleNo;
        }

        public int getCycleNo() {
            return cycleNo;
        }

        public boolean isCancel() {
            return cancel;
        }

        public void setCancel(boolean cancel) {
            this.cancel = cancel;
        }

        public Task() {
        }

        public Task(int index, int cycleNo) {
            this.index = index;
            this.cycleNo = cycleNo;
        }

        @Override
        public void run() {

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Task task = (Task) o;
            return index == task.index &&
                    key == task.key &&
                    cycleNo == task.cycleNo &&
                    cancel == task.cancel &&
                    userId == task.userId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, key, cycleNo, cancel, userId);
        }
    }


    /**
     * Constructor of RingTimingWheel withe default bufferSize .
     *
     * @param executorService
     */
    public TcpConnectUtil(ExecutorService executorService) {
        this.bufferSize = DEFAULT_BUFFER_SIZE;
        this.bufferData = new Object[DEFAULT_BUFFER_SIZE];
        this.executorService = executorService;
    }

    /**
     *  Constructor of RingTimingWheel, construct bufferSize and executorService to execute tasks.
     *
     * @param bufferSize        The RingTimingWheel size.
     *                          Note that bufferSize should be  the power of 2, if not, it will be replace
     *                          of the power of 2, eg 48,57,63 will be replaced with 64.
     * @param executorService   The thread pools to execute relay task, execute one task per seconds.
     */
    public TcpConnectUtil(int bufferSize, ExecutorService executorService) {
        if (!isPowerOf2(bufferSize)) {
            bufferSize = tableSizeFor(bufferSize);
        }
        this.bufferSize = bufferSize;
        this.bufferData = new Object[bufferSize];
        this.executorService = executorService;
    }

    /**
     * Add new task, thread safe.
     *
     * @param task
     * @return
     */
    public long addTask(Task task) {
        if (stop.get()) {
            throw new IllegalStateException("The job is stop.");
        }

        // Calculate the task mode, find out location in RingTimingWheel.
        int key = task.getKey();
        int indexInRingBuffer = mod(key);

        lock.lock();
        try {
            task.setIndex(indexInRingBuffer);
            Set<Task> tasks = (Set<Task>) bufferData[indexInRingBuffer];
            // if indexInRingBuffer location has no task, add current task directly.
            if (tasks == null) {
                task.setCycleNo(calculateCycleNo(key));
                tasks = new HashSet<>();
                tasks.add(task);
            } else {
                task.setCycleNo(0);
                tasks.add(task);
            }

            if (map.containsKey(task.getUserId())) {
                // remove last task.
                Set<Task> tasks2 = (Set<Task>) bufferData[map.get(task.getUserId())];
                tasks2.stream().forEach(task2 -> {
                    if (task2.getUserId() == task.getUserId()) {
                        task2.setCancel(true);
                    }
                });
            }
            // new position
            map.put(task.getUserId(), indexInRingBuffer);

            put(indexInRingBuffer, tasks);

        } finally {
            lock.unlock();
        }

        if (start.compareAndSet(false, true)) {
            startJob();
        }

        return addTasksCount.incrementAndGet();
    }


    /**
     * Stop the job.
     *
     * @param force if true, will shutdown the threadPool immediately,
     *              else false, the main thread will wait until addTasks which be executed finished.
     *
     */
    public void stop(boolean force) {
        if (force) {
            logger.info("Delay job is force to stop");
            if (stop.compareAndSet(false, true)) {
                executorService.shutdownNow();
            }
        } else {
            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                // ...
            } finally {
                lock.unlock();
            }

            stop.compareAndSet(false, true);
            try {
                executorService.shutdown();
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                    if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                        logger.error("Delay job thread pool shut down failed.");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Start a new thread to execute tasks in RingTimingWheel.
     */
    private void  startJob() {
        new Thread(() -> {
            logger.info("RingTimingWheel job is start.");
            while (!stop.get()) {
                Set<Task> tasks = removeTask(ringBufferCursor.get());

                // Re-cycle
                if (ringBufferCursor.incrementAndGet() > bufferSize - 1) {
                    ringBufferCursor.set(0);
                }

                if (tasks.size() > 0) {
                    for (Task task : tasks) {
                        if (!task.cancel) {
                            executorService.submit(task);
                            executedTasksCount.incrementAndGet();
                        }
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // ...
                }
            }
        }).start();
    }


    private void size2Notify() {
        lock.lock();
        try {
            if (addTasksCount.get() == 0) {
                condition.signal();
            }
        } finally {
            lock.unlock();
        }

    }

    private Set<Task> removeTask(int index) {
        Set<Task> tasks = (Set<Task>) bufferData[index];
        if (tasks == null) {
            return new HashSet<>();
        }

        // Wait to be executed tasks.
        Set<Task> copy = new HashSet<>();
        Set<Task> result = new HashSet<>();
        for (Task task : tasks) {
            if (task.getCycleNo() == 0 && !task.cancel) {
                result.add(task);
                addTasksCount.decrementAndGet();
                size2Notify();
            } else {
                task.setCycleNo(task.getCycleNo() - 1);
                copy.add(task);
            }
        }

        // remove executed task
        bufferData[index] = copy;

        return result;
    }

    private void put(int indexInRingBuffer, Set<Task> tasks) {
        bufferData[indexInRingBuffer] = tasks;
    }

    private int calculateCycleNo(int key) {
        return key / bufferSize;
    }

    /**
     * Return the task location in RingTimingWheel.
     * First find current index in RingBuffer:ringBufferCursor, then add current key.
     * (Why current key add ringBufferCursor? it means delay key seconds the ringBufferCursor
     *  will move key+ringBufferCursor step.)
     * If bufferSize is power of 2, key & (bufferSize - 1) equals key % bufferSize
     *
     * @param key delay time of task
     * @return
     */
    private int mod(int key) {
        key += ringBufferCursor.get();
        return key & (bufferSize - 1);
    }

    /**
     * Returns a power of two size for the given target capacity.
     */
    private int tableSizeFor(int bufferSize) {
        int n = bufferSize - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

    private boolean isPowerOf2(int bufferSize) {
        return bufferSize % 2 == 0;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        TcpConnectUtil ringTimeWheel = new TcpConnectUtil(executorService);

        TcpConnectUtil.Task task = new OrderCancelJob(111111111);
        task.setKey(5);
        ringTimeWheel.addTask(task);
        System.out.println(((OrderCancelJob) task).orderId + "添加job成功， " + DateTime.now().toString());

    }


    static class OrderCancelJob extends TcpConnectUtil.Task {
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

}

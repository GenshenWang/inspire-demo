package com.wgs.parrot.优雅关闭_003;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池优雅关闭时通过 Runtime.getRuntime().addShutdownHook 钩子实现的
 * addShutdownHook的使用场景有：
 * （1）关闭连接：数据同步神器 Canal 借助它，来进行关闭 socket 链接、释放 canal 的工作节点、清理缓存信息等；
 * （2）释放资源，关闭工作中的线程：海量日志收集 Flume 借助它，来实现线程池资源关闭、工作线程停止等；
 * （3）应用正常退出时，执行特定的业务逻辑、关闭资源等
 */
public class ThreadShutdownDemo {

    private ScheduledExecutorService scheduledExecutor;
    private AtomicInteger counter = new AtomicInteger();

    public ThreadShutdownDemo(ScheduledExecutorService scheduledExecutor) {
        this.scheduledExecutor = scheduledExecutor;
        // 添加钩子，实现优雅提服
        Runtime.getRuntime().addShutdownHook(new Thread("ThreadPoolShutdownThread") {
            @Override
            public void run() {
                System.out.println("容器即将关闭。。。");
                stopGracefully();
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread("TestHookThread") {
            @Override
            public void run() {
                System.out.println("测试钩子可以挂多个");
            }
        });
    }

    public void start() {
        System.out.println("Thread pool start.");
        scheduledExecutor.scheduleAtFixedRate(() -> {
            System.out.println("线程开始启动了，心跳上报：" + counter.incrementAndGet());
        },1, 1, TimeUnit.SECONDS);
    }

    public void stop() {
        System.out.println("Thread pool is try to stop.");
        System.out.println("线程池是否shutdown: " + scheduledExecutor.isShutdown());
        System.out.println("线程池是否关闭 " + scheduledExecutor.isTerminated());
    }

    /**
     * 优雅关闭机制
     *
     * 注意：如果是kill -9 程序会直接退出
     *      只有kill 命令才能配合使用
     */
    public void stopGracefully() {
        System.out.println("Thread pool is try to stop gracefully.");
        if (scheduledExecutor != null) {
            scheduledExecutor.shutdown();
            try {
                // 等了60s还没关闭就强行关闭
                if (!scheduledExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                    scheduledExecutor.shutdownNow();
                    // wait another 1 min
                    scheduledExecutor.awaitTermination(60, TimeUnit.SECONDS);
                    if (!scheduledExecutor.isTerminated()) {
                        System.out.println("线程池关闭失败.");
                        return;
                    }
                }
            } catch (InterruptedException e) {
                scheduledExecutor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("线程池是否shutdown: " + scheduledExecutor.isShutdown());
        System.out.println("线程池是否关闭 " + scheduledExecutor.isTerminated());
    }


    public static void main(String[] args) {
        ThreadShutdownDemo demo = new ThreadShutdownDemo(Executors.newScheduledThreadPool(2));
        demo.start();
    }

}

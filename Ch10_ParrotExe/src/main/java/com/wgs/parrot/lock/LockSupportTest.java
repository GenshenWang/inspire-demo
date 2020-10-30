package com.wgs.parrot.lock;

import org.joda.time.DateTime;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {


    public static void main(String[] args) throws InterruptedException {
        LockSupportTest demo = new LockSupportTest();

        Thread thread = new Thread(() -> {
            demo.threadPark();
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread2");
            demo.threadPark();
        });

        thread.start();
        thread2.start();
        Thread.sleep(3000);
        LockSupport.unpark(thread);
    }

    public void threadPark() {
        System.out.println("线程执行1 " + DateTime.now().toString());
        LockSupport.park();
        System.out.println("线程执行2 " + DateTime.now().toString());
    }
}

package com.wgs.parrot.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private ReentrantLock lock = new ReentrantLock();

    public void acquireLock() {
        String threadName = Thread.currentThread().getName();

        try {
            System.out.println("Thread-" + threadName + "尝试获取锁");
            lock.lockInterruptibly();
            System.out.println("Thread-" + threadName + "成功获取到锁。");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Thread-" + threadName + "获取锁过程中遇到异常。" + e.toString());
        } finally {
            try {
                lock.unlock();
                System.out.println("Thread-" + threadName + "释放锁。");
            } catch (Exception e) {
                System.out.println("Thread-" + threadName + "释放锁异常" + e.toString());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        Thread threa1 = new Thread(() -> {
            reentrantLockTest.acquireLock();
        }, "ThreadA");

        Thread threa2 = new Thread(() -> {
            reentrantLockTest.acquireLock();
        }, "ThreadB");

        threa1.start();
        Thread.sleep(10);
        threa2.start();
        Thread.sleep(30);

        threa2.interrupt();


    }
}

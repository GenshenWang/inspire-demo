package com.wgs.parrot.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Ticket {

    private final ReentrantLock lock = new ReentrantLock();
    private final AtomicInteger counter = new AtomicInteger(100);
    private int totalTicket = 1000;

    public void sellNotThreadSafe() {
        System.out.println("售票员" + Thread.currentThread().getName() + "售卖了1张票，当前票量剩余"
                + --totalTicket);
    }

    public synchronized void sellThreadSafeWithSynchronized() {
        System.out.println("售票员" + Thread.currentThread().getName() + "售卖了1张票，当前票量剩余"
                + --totalTicket);
    }

    public void sellThreadSafeWithLock() {
        lock.lock();
        try {
            System.out.println("售票员" + Thread.currentThread().getName() + "售卖了1张票，当前票量剩余"
                    + --totalTicket);
        } finally {
            lock.unlock();
        }
    }
}

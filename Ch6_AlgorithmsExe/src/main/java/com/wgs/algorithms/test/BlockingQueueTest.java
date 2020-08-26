package com.wgs.algorithms.test;

public class BlockingQueueTest<E> {
    private int size;
    private volatile static int count;
    private Object[] queue;

    public BlockingQueueTest(int size) {
        this.size = size;
        queue = new Object[size];
    }

    public synchronized boolean enQueue(E item) {
        while (count == size) {
            try {
                wait(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        queue[count++] = item;
        notifyAll();
        return true;
    }

    public E deQueue() {
        while (count == 0) {
            try {
                wait(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Object obj = queue[0];
        count--;
        return (E) obj;
    }
}

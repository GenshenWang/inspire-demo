package com.wgs.algorithms.test;

public class ArrayQueue<E> {
    private int capacity;
    private int head;
    private int tail;
    private Object[] queue;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new Object[capacity];
        this.tail = 0;
        this.head = 0;
    }

    public boolean enQueue2(E item) {
        if (tail == capacity && head == 0) {
            return false;
        } else {
            // 数据搬移
            for (int i = head; i < tail; i++) {
                queue[i - head] = queue[i];
            }

            tail = tail - head;
            head = 0;
        }
        queue[tail++] = item;
        return true;
    }

    public E deQueue() {
        if (tail == head) {
            return null;
        }
        Object obj = queue[head++];
        return (E) obj;
    }
}

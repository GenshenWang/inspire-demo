package com.wgs.algorithms.队列;

/**
 * @author: wanggenshen
 * @date: 2020/5/19 13:06.
 * @description: 队列实现1: 数组
 */
public class ArrayQueue<E> implements MyQueue<E> {

    private Object[] queue;
    private int queueSize;
    private int queueCount;

    public ArrayQueue(int queueSize) {
        queue = new Object[queueSize];
        this.queueSize = queueSize;
        this.queueCount = 0;
    }

    @Override
    public void enqueue(E node) {
        if (queueCount + 1 > queueSize) {
            throw new RuntimeException("Exceed queue size");
        }

        queue[queueCount] = node;
        queueCount++;
    }

    /**
     * 出队时, 整体往前移动一位, 所以时间复杂度为O(n)
     * @return
     */
    @Override
    public E dequeue() {
        if (queue == null || queueCount <= 0) {
            return null;
        }

        E obj = (E) queue[0];
        // 0 1 2 3, 出0, 则将1 2 3往前移一位
        System.arraycopy(queue, 1, queue, 0, queueCount - 1);

        // clear to let GC collect it
        queue[--queueCount] = null;
        return obj;
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new ArrayQueue<>(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        queue.enqueue(4);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        System.out.println(queue.dequeue());
    }
}

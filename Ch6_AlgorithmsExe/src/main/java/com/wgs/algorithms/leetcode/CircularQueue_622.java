package com.wgs.algorithms.leetcode;

public class CircularQueue_622 {
    private int head;
    //private int tail;
    private int n;
    private int count;
    private int[] nums;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public CircularQueue_622(int k) {
        nums = new int[k];
        this.n = k;
        this.count = 0;
        this.head = 0;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }

        nums[(head + count) % n] = value;
        count++;

        return true;

    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        head = (head + 1) % n;
        count--;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (count == 0) {
            return -1;
        }
        return nums[head];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (count == 0) {
            return -1;
        }
        int tail = head + count - 1;
        return nums[tail % n];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return count == 0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return count == n;
    }

    public static void main(String[] args) {
        CircularQueue_622 circularQueue = new CircularQueue_622(6);
        System.out.println(circularQueue.enQueue(6));
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.enQueue(5));
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.deQueue());

        System.out.println(circularQueue.Front());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.deQueue());


       /* System.out.println(circularQueue.enQueue(3));
        System.out.println(circularQueue.enQueue(1));
        System.out.println(circularQueue.enQueue(2));
        System.out.println(circularQueue.enQueue(3));
        System.out.println(circularQueue.enQueue(4));
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.isFull());
        System.out.println(circularQueue.enQueue(4));

        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.Rear());*/

        circularQueue.enQueue(3);
    }
}

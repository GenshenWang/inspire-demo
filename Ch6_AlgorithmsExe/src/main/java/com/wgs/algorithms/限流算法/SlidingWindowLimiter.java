package com.wgs.algorithms.限流算法;

/**
 * @author: wanggenshen
 * @date: 2020/6/29 21:56.
 * @description: 循环队列实现滑动窗口限流算法
 */
public class SlidingWindowLimiter {

    private int windowSize;
    private CircularQueue queue;

    public SlidingWindowLimiter(int windowSize) {
        this.windowSize = windowSize;
        queue = new CircularQueue(windowSize);
    }

    public boolean tryAcquire(long now) {

        // 判断是否有间隔1s的请求, 有则移除队列
        while (queue.prevNode() != -1 && now - queue.prevNode() > 1000) {
            System.out.println("超过1S间隔, 移除超过间隔的节点: " + queue.prevNode() + "当前时间: " + now + ", 间隔: " + (now - queue.prevNode()));
            queue.dequeue();
        }

        // 队列已满, 拒绝访问
        if (queue.isFull()) {
            System.out.println("队列已满, now: " + now);
            return false;
        }

        queue.enqueue(now);
        return true;
    }

    static class CircularQueue {
        /**
         * 每次请求的时间戳
         */
        private long[] timeQueue;

        /**
         * 队列大小
         */
        private int size;

        /**
         * 头指针
         */
        private int headIndex;

        /**
         * 尾指针
         */
        private int tailIndex;


        public CircularQueue(int size) {
            // 循环队列尾部指针多占用一个单元格
            timeQueue = new long[size + 1];
            this.size = size + 1;
        }

        /**
         * 入队
         */
        public void enqueue (long timestamp) {

            // 队列已满
            if (isFull()) {
                throw new RuntimeException("Exceed queue size.");
            }

            timeQueue[tailIndex] = timestamp;
            tailIndex = (tailIndex + 1) % size;
        }

        /**
         * 出队
         *
         * @return
         */
        public long dequeue () {
            // 队列为空
            if (isEmpty()) {
                return -1;
            }

            long timestamp = timeQueue[headIndex];
            headIndex = (headIndex + 1) % size;
            return timestamp;
        }

        public long prevNode() {
            // 队列为空
            if (isEmpty()) {
                return -1;
            }
            return timeQueue[headIndex];
        }

        public boolean isFull() {
            return (tailIndex + 1) % size == headIndex;
        }

        public boolean isEmpty() {
            return tailIndex == headIndex;
        }
    }


    public static void main(String[] args) {
        SlidingWindowLimiter limiter = new SlidingWindowLimiter(6);
        long now = System.currentTimeMillis();
        System.out.println("结果:" + limiter.tryAcquire(now) + ", 时间戳: " + now);
        sleep(100);

        now = System.currentTimeMillis();
        System.out.println("结果:" + limiter.tryAcquire(now) + ", 时间戳: " + now);
        sleep(100);

        now = System.currentTimeMillis();
        System.out.println("结果:" + limiter.tryAcquire(now) + ", 时间戳: " + now);
        sleep(100);

        now = System.currentTimeMillis();
        System.out.println("结果:" + limiter.tryAcquire(now) + ", 时间戳: " + now);
        sleep(100);

        now = System.currentTimeMillis();
        System.out.println("结果:" + limiter.tryAcquire(now) + ", 时间戳: " + now);
        sleep(100);

        now = System.currentTimeMillis();
        System.out.println("结果:" + limiter.tryAcquire(now) + ", 时间戳: " + now);
        sleep(600);

        now = System.currentTimeMillis();
        System.out.println("结果:" + limiter.tryAcquire(now) + ", 时间戳: " + now);
        sleep(100);

        now = System.currentTimeMillis();
        System.out.println("结果:" + limiter.tryAcquire(now) + ", 时间戳: " + now);
        sleep(100);

    }

    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

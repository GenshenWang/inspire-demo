package com.wgs.algorithms.队列;

/**
 * @author: wanggenshen
 * @date: 2020/5/20 10:18.
 * @description: 阻塞队列实现 (有限大小队列)
 */
public class MyBlockingQueue<E> implements MyQueue<E>{


    private Object[] queue;
    private int size;
    private int count;

    public MyBlockingQueue(int size) {
        this.queue = new Object[size];
        this.size = size;
    }


    @Override
    public synchronized void enqueue(E node) {
        // 队列满了, 就阻塞
        while (count == size) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 队列为空, 唤醒其他线程
        if (count == 0) {
            notifyAll();
        }

        queue[count++] = node;
    }

    @Override
    public synchronized E dequeue() {
        // 队列满了, 就阻塞
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 队列为空, 唤醒其他线程
        if (count == size) {
            notifyAll();
        }

        Object obj = queue[0];
        System.arraycopy(queue, 1, queue, 0, count - 1);
        count--;
        return (E) obj;
    }

    public static void main(String[] args) {
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(10);

        Thread thread1 = new Thread(new Runnable() {
            int count = 0;
            @Override
            public void run() {
                while (count < 100) {
                    System.out.println("Thread1 入队Node: " + count);
                    queue.enqueue(count++);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int node = 0;
                while (node < 100) {
                    node = queue.dequeue();
                    System.out.println("++++++++Thread2 出队Node: " + node);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        thread2.start();
        thread1.start();

    }
}

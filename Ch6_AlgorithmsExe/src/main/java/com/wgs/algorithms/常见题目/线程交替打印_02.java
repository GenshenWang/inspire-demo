package com.wgs.algorithms.常见题目;

/**
 * https://cloud.tencent.com/developer/article/1650130
 */
public class 线程交替打印_02 {

    private volatile int num = 0;
    private final Object lock = new Object();


    public void print() {

        synchronized (lock) {
            while (num <= 10) {
                try {
                    System.out.println(num++);
                    lock.notifyAll();
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static void main(String[] args) {
        线程交替打印_02 a = new 线程交替打印_02();
        Thread thread1 = new Thread(a::print, "thead1");
        Thread thread2 = new Thread(a::print, "thead2");

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                a.print();
            }
        });

        thread1.start();
        thread2.start();

        //new 线程交替打印_02().print();
    }
}

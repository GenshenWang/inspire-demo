package com.wgs.algorithms.test;

public class AtomicTest {

    private static int count;

    public static void incr() {
        count++;
    }

    public static void main(String[] args) {

        Thread[] threads = new Thread[10000];
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    incr();
                }
            });

            threads[i].start();
        }

        //应该是200000 但是由于并发，最后结果不是
        System.out.println(count);
    }
}

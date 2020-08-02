package com.wgs.algorithms.常见题目;

public class 线程交替打印_01 {

    private volatile int num = 0;

    public void print() {

        new Thread(() -> {
            while (true) {
                if (num % 2 == 0 && num <= 10) {
                    System.out.println(num);
                    num++;
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                if (num % 2 == 1 && num <= 10) {
                    System.out.println(num);
                    num++;
                }
            }
        }).start();
    }


    public static void main(String[] args) {
        new 线程交替打印_01().print();
    }
}

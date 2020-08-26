package com.wgs.algorithms.test;

public class SynDemo {

    private int age = 0;
    private static int weight = 0;

    public synchronized void printA(){
        for (int i = 0; i < 10; i++) {
            System.out.println(age++);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void printC(){
        System.out.println("非同步方法C");
    }

    public static synchronized void printB(){
        for (int i = 0; i < 10; i++) {
            System.out.println(weight++);
        }
    }

    private Object lock = new Object();

    public  void printD(){

        System.out.println("代码块内容之前");
        synchronized (SynDemo.class){
            System.out.println("代码块内容");

            try {
                System.out.println("====wait====");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("代码块内容之后");

    }

    /**
     * 锁粗化
     */
    public  void printE(){



        /*for (int i = 0; i < 10; i++) {
            System.out.println("代码块内容之前");
            synchronized (this) {
                System.out.println("synchronized代码块内容：" + i);
            }
        }*/

        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println("代码块内容之前");
                System.out.println("synchronized代码块内容：" + i);
            }
        }


        System.out.println("代码块内容之后");

    }

    public static void main(String[] args) {
        SynDemo synDemo = new SynDemo();
        SynDemo synDemo2 = new SynDemo();
        Thread thread1 = new Thread(() -> synDemo.printE());
        Thread thread2 = new Thread(() -> synDemo2.printE());
        thread1.start();
        thread2.start();

       /* SynDemo synDemo = new SynDemo();
        SynDemo synDemo2 = new SynDemo();
        Thread thread1 = new Thread(() -> synDemo.printB());
        Thread thread2 = new Thread(() -> synDemo2.printB());
        thread1.start();
        thread2.start();*/
    }
}

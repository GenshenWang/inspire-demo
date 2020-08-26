package com.wgs.algorithms.test;

public class VolatileDemo {


    public static void main(String[] args) {

        User user = new User("aaa", 12);
        new Thread(() -> {
            user.setAge(user.getAge() + 1);
            System.out.println(user.getAge());
        }).start();

        new Thread(() -> {
            user.setAge(user.getAge() + 1);
            System.out.println(user.getAge());
        }).start();

        new Thread(() -> {
            user.setAge(user.getAge() + 1);
            System.out.println(user.getAge());
        }).start();

        new Thread(() -> {
            user.setAge(user.getAge() + 1);
            System.out.println(user.getAge());
        }).start();
    }
}

package com.wgs.codedesign.享元模式;

/**
 * @author: wanggenshen
 * @date: 2020/4/4 14:58.
 * @description: 使用享元模式实现一个类似Integer的功能
 */
public class MyInteger {

    private int value;

    public MyInteger(int value) {
        this.value = value;
    }

    public static MyInteger valueOf(int i) {
        // 从享元模式中缓存的对象返回
        if (i >= MyIntegerFactory.low && i <= MyIntegerFactory.high) {
            return MyIntegerFactory.cache[i + (- MyIntegerFactory.low)];
        }

        // 直接new一个对象
        return new MyInteger(i);

    }

    private static class MyIntegerFactory {
        static final int low = -128;
        static final int high = 127;
        static MyInteger[] cache = null;

        static {
            // 存储一个字节的大小 (2^8 = 256)
            cache = new MyInteger[high - low + 1];
            int j = low;
            for (int i = 0; i < cache.length; i++) {
                cache[i] = new MyInteger(j++);
            }
        }
    }

    public static void main(String[] args) {
        Integer a = 10;
        Integer b = 10;
        Integer c = new Integer(10);
        System.out.println(a == b); // true
        System.out.println(a == c); // false
        System.out.println(b == c); // false

        // MyInteger.valueOf(10) 效果等于 Integer a = 10; 自动装箱
        MyInteger myInteger1 = MyInteger.valueOf(10);
        MyInteger myInteger2 = MyInteger.valueOf(10);
        MyInteger myInteger3 = new MyInteger(10);
        System.out.println(myInteger1 == myInteger2); // true
        System.out.println(myInteger1 == myInteger3); // false
        System.out.println(myInteger2 == myInteger3); // false

    }
}

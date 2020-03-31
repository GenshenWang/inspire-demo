package com.wgs.codedesign.适配器模式.demo_类适配;

/**
 * @author: wanggenshen
 * @date: 2020/3/31 21:24.
 * @description: 类适配, 通过继承的方式
 */
public class Adaptor extends OrderDemo2 implements IOrderDemo {
    @Override
    public void fun1() {
        super.fun3();
    }

    @Override
    public void fun2() {
        super.fun4();
    }
}

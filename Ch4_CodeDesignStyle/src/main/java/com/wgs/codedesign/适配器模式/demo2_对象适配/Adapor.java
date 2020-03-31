package com.wgs.codedesign.适配器模式.demo2_对象适配;


/**
 * @author: wanggenshen
 * @date: 2020/3/31 21:24.
 * @description: 对象适配, 通过组合的方式
 */
public class Adapor implements IOrderDemo {

    private OrderDemo2 orderDemo2;

    public Adapor(OrderDemo2 orderDemo2) {
        this.orderDemo2 = orderDemo2;
    }

    @Override
    public void fun1() {
        orderDemo2.fun3();
    }

    @Override
    public void fun2() {
        orderDemo2.fun4();
    }
}

package com.wgs.codedesign.适配器模式.demo2_对象适配;


/**
 * @author: wanggenshen
 * @date: 2020/3/31 21:26.
 * @description: XXX
 */
public class DemoMain2 {

    public static void main(String[] args) {
        IOrderDemo iOrderDemo1 = new Adapor(new OrderDemo2());
        // 调用iOrderDemo1, 实际上调用的是iOrderDemo2的fun3
        iOrderDemo1.fun1();
        iOrderDemo1.fun2();
    }
}

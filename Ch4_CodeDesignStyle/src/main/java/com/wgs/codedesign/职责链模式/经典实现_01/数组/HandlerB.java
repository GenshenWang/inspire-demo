package com.wgs.codedesign.职责链模式.经典实现_01.数组;

/**
 * @author: wanggenshen
 * @date: 2020/4/15 23:02.
 * @description: XXX
 */
public class HandlerB implements Handler {
    @Override
    public boolean handle(String msg) {
        System.out.println("HandlerB 开始处理了");
        return true;
    }
}

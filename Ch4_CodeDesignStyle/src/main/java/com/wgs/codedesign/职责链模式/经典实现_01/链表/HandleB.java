package com.wgs.codedesign.职责链模式.经典实现_01.链表;

/**
 * @author: wanggenshen
 * @date: 2020/4/15 21:59.
 * @description: XXX
 */
public class HandleB extends Handler {

    @Override
    public boolean doHandle(String msg) {
        System.out.println("HandleB开始处理了");
        return true;
    }
}

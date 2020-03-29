package com.wgs.codedesign.桥接模式.v3.handler;

/**
 * @author: wanggenshen
 * @date: 2020/3/29 23:08.
 * @description: XXX
 */
public class OrderMsgHandler implements MsgHandler {
    @Override
    public void processMsg(String msg) {
        System.out.println("start to process order msg");
    }
}

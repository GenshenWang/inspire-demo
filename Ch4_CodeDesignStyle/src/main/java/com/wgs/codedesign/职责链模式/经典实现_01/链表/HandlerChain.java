package com.wgs.codedesign.职责链模式.经典实现_01.链表;

/**
 * @author: wanggenshen
 * @date: 2020/4/15 22:01.
 * @description: XXX
 */
public class HandlerChain {
    private Handler head = null;
    private Handler tail = null;

    public void addHandler(Handler handler) {
        handler.setNext(null);

        if (head == null) {
            head = handler;
            tail = handler;
            return;
        }
        tail.setNext(handler);
        tail = handler;
    }

    /**
     * 入口方法
     *
     * @param msg
     */
    public void handler(String msg) {
        if (head != null) {
            head.handle(msg);
        }
    }


    public static void main(String[] args) {
        Handler handlerA = new HandleA();
        Handler handlerB = new HandleB();

        HandlerChain handlerChain = new HandlerChain();
        handlerChain.addHandler(handlerA);
        handlerChain.addHandler(handlerB);

        String msg = "原始信息";
        handlerChain.handler(msg);
        System.out.println(msg);
    }

}

package com.wgs.codedesign.职责链模式.经典实现_01.数组;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/4/15 23:04.
 * @description: XXX
 */
public class HandlerChain {

    private List<Handler> handlers = new ArrayList<>();

    public void addHandler(Handler handler) {
        this.handlers.add(handler);
    }

    public void handle(String msg) {
        for (Handler handler : handlers) {
            handler.handle(msg);
        }
    }

    public static void main(String[] args) {
        HandlerChain handlerChain = new HandlerChain();
        handlerChain.addHandler(new HandlerA());
        handlerChain.addHandler(new HandlerB());

        handlerChain.handle("原始信息2");

    }
}

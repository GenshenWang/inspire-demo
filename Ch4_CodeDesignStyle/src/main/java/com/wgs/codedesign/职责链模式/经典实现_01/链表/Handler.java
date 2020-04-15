package com.wgs.codedesign.职责链模式.经典实现_01.链表;

/**
 * @author: wanggenshen
 * @date: 2020/4/15 21:57.
 * @description: XXX
 */
public abstract class Handler {

    protected Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    /**
     * 使用模板方法
     *
     * @param msg
     */
    public final void handle(String msg) {
        // 由子类处理
        boolean done = doHandle(msg);

        // 当前处理器完成, 交由下一个处理器处理
        if (done && next != null) {
            next.handle(msg);
        }
    }

    public abstract boolean doHandle(String msg);
}

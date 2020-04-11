package com.wgs.codedesign.观察者模式.v1_经典代码.observer;

import com.wgs.codedesign.观察者模式.v1_经典代码.Message;

/**
 * @author: wanggenshen
 * @date: 2020/4/7 12:23.
 * @description: XXX
 */
public class ConcreteObserverOne implements Observer {
    @Override
    public void update(Message message) {
        System.out.println("获取被观察者对象消息变更, 观察者1号开始执行自己的逻辑");
    }
}

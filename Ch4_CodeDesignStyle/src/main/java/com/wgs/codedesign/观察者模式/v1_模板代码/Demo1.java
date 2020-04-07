package com.wgs.codedesign.观察者模式.v1_模板代码;

import com.wgs.codedesign.观察者模式.v1_模板代码.observer.ConcreteObserverOne;
import com.wgs.codedesign.观察者模式.v1_模板代码.observer.ConcreteObserverTwo;
import com.wgs.codedesign.观察者模式.v1_模板代码.subject.ConcreteSubject;
import com.wgs.codedesign.观察者模式.v1_模板代码.subject.Subject;

/**
 * @author: wanggenshen
 * @date: 2020/4/7 12:28.
 * @description: XXX
 */
public class Demo1 {

    public static void main(String[] args) {
        Message message = new Message("事件变更");

        Subject subject = new ConcreteSubject();
        subject.registerObserver(new ConcreteObserverOne());
        subject.registerObserver(new ConcreteObserverTwo());

        subject.notifyObservers(message);
    }
}

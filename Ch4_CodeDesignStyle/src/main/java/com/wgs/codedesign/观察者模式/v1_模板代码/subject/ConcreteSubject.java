package com.wgs.codedesign.观察者模式.v1_模板代码.subject;

import com.google.common.collect.Lists;
import com.wgs.codedesign.观察者模式.v1_模板代码.Message;
import com.wgs.codedesign.观察者模式.v1_模板代码.observer.Observer;

import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/4/7 12:26.
 * @description: XXX
 */
public class ConcreteSubject implements Subject {

    // 观察者对象集合
    List<Observer> observers = Lists.newArrayList();
    
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Message message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

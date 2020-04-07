package com.wgs.codedesign.观察者模式.v1_模板代码.subject;

import com.wgs.codedesign.观察者模式.v1_模板代码.Message;
import com.wgs.codedesign.观察者模式.v1_模板代码.observer.Observer;

/**
 * @author: wanggenshen
 * @date: 2020/4/7 11:32.
 * @description: Subject类似于一个Manager功能, 用于注册、管理观察者,
 *               以及事件发生时通知观察者事件变更的操作
 */
public interface Subject {

    /**
     * 注册观察者
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 移除观察者
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知观察者
     * @param message
     */
    void notifyObservers(Message message);
}

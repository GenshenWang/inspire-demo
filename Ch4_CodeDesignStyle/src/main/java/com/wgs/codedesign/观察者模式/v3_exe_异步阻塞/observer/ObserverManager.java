package com.wgs.codedesign.观察者模式.v3_exe_异步阻塞.observer;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/4/7 13:29.
 * @description: XXX
 */
public class ObserverManager {

    List<UserRegisterObserver> observers = Lists.newArrayList();

    public void registerObserver(UserRegisterObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(UserRegisterObserver toRemoveObserver) {
        Iterator<UserRegisterObserver> iterator = observers.iterator();
        while (iterator.hasNext()) {
            UserRegisterObserver observer = iterator.next();
            if (toRemoveObserver.equals(observer)) {
                iterator.remove();
            }
        }
    }

    public void executeObserver(long userId) {
        for (UserRegisterObserver observer : observers) {
            observer.handleRegisterEvent(userId);
        }
    }
}

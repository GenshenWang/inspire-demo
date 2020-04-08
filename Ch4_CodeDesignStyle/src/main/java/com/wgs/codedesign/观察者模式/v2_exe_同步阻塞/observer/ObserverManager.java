package com.wgs.codedesign.观察者模式.v2_exe_同步阻塞.observer;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: wanggenshen
 * @date: 2020/4/7 13:29.
 * @description: XXX
 */
public class ObserverManager {

    private List<UserRegisterObserver> observers = Lists.newArrayList();
    private static final ExecutorService executor = new ThreadPoolExecutor(
            5,
            10,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024),
            new ThreadFactoryBuilder().setDaemon(true).setNameFormat("Login-Observer-Service-%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

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
            // 使用线程池实现异步阻塞的观察者模式
            executor.execute(() -> observer.handleRegisterEvent(userId));

        }
    }
}

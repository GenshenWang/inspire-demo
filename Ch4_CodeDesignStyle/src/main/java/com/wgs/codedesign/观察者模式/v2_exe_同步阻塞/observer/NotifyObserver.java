package com.wgs.codedesign.观察者模式.v2_exe_同步阻塞.observer;

import com.wgs.codedesign.观察者模式.v2_exe_同步阻塞.service.NotifyService;

/**
 * @author: wanggenshen
 * @date: 2020/4/7 13:27.
 * @description: XXX
 */
public class NotifyObserver implements UserRegisterObserver{
    private NotifyService notifyService;

    public NotifyObserver() {
        this.notifyService = new NotifyService();
    }


    @Override
    public void handleRegisterEvent(long userId) {
        notifyService.notify(userId);
    }
}

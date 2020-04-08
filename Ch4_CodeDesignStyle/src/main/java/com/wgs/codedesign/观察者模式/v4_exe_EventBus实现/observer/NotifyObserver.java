package com.wgs.codedesign.观察者模式.v4_exe_EventBus实现.observer;

import com.google.common.eventbus.Subscribe;
import com.wgs.codedesign.观察者模式.common_service.NotifyService;

/**
 * @author: wanggenshen
 * @date: 2020/4/7 13:27.
 * @description: XXX
 */
public class NotifyObserver {
    private NotifyService notifyService;

    public NotifyObserver() {
        this.notifyService = new NotifyService();
    }


    @Subscribe
    public void registerNotify(Long userId) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyService.notify(userId);
    }
}

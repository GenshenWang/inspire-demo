package com.wgs.codedesign.观察者模式.v2_exe_login.observer;

import com.wgs.codedesign.观察者模式.v1_模板代码.Message;
import com.wgs.codedesign.观察者模式.v1_模板代码.observer.Observer;
import com.wgs.codedesign.观察者模式.v2_exe_login.service.NotifyService;

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

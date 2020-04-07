package com.wgs.codedesign.观察者模式.v2_exe_login.service;

/**
 * @author: wanggenshen
 * @date: 2020/4/7 13:21.
 * @description: XXX
 */
public class NotifyService {

    public void notify(long userId) {
        System.out.println("注册成功, 发送站内信通知:" + userId);
    }
}

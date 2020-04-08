package com.wgs.codedesign.观察者模式.v2_exe_同步阻塞.observer;

/**
 * @author: wanggenshen
 * @date: 2020/4/7 13:20.
 * @description: XXX
 */
public interface UserRegisterObserver {

    void handleRegisterEvent(long userId);
}

package com.wgs.codedesign.观察者模式.v5_exe_myEventBus;

import java.util.HashMap;

/**
 * @author: wanggenshen
 * @date: 2020/4/9 01:01.
 * @description: 带有注解的方法注册信息
 */
public class ObserverRegistry {

    private static final HashMap<Class<?>, ObserverAction> map = new HashMap();

    public static void register(Object ovserver) {

    }
}

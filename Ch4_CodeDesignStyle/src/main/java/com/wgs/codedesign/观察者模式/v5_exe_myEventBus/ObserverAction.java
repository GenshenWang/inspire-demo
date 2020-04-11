package com.wgs.codedesign.观察者模式.v5_exe_myEventBus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: wanggenshen
 * @date: 2020/4/9 00:59.
 * @description: 执行带有`@MySubscribe`注解的方法
 */
public class ObserverAction {
    // 带有`@MySubscribe`注解的目标类
    private Object targetClass;
    // 目标类中带有`@MySubscribe`注解的方法
    private Method method;

    public ObserverAction(Object targetClass, Method method) {
        this.targetClass = targetClass;
        this.method = method;
        this.method.setAccessible(true);
    }

    /**
     * 执行带有`@MySubscribe`注解的方法
     *
     * @param event 目标方法的参数
     */
    public void execute(Object event) {
        try {
            method.invoke(targetClass, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

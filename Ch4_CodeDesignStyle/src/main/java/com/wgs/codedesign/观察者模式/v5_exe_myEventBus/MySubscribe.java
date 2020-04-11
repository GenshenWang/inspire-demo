package com.wgs.codedesign.观察者模式.v5_exe_myEventBus;

import com.google.common.annotations.Beta;

import java.lang.annotation.*;

/**
 * @author: wanggenshen
 * @date: 2020/4/9 00:57.
 * @description: XXX
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Beta
public @interface MySubscribe {
}

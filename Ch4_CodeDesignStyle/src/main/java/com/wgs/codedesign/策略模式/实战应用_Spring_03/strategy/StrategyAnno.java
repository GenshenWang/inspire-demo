package com.wgs.codedesign.策略模式.实战应用_Spring_03.strategy;

import java.lang.annotation.*;

/**
 * @author: wanggenshen
 * @date: 2020/4/14 22:56.
 * @description: XXX
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface StrategyAnno {

    String type();
}

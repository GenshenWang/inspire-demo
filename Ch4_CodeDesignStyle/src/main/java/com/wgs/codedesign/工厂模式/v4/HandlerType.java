package com.wgs.codedesign.工厂模式.v4;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author wanggenshen
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface HandlerType {


    Constant.HandlerTypeEnum handlerType();
}

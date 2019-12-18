package com.wgs.codedesign.ocp.v2.interceptor.annotation;

import java.lang.annotation.*;

/**
 * @author wanggenshen
 * @date 2019/12/11 18:01.
 * Description 定义拦截器顺序
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface InterceptorOrder {

    /**
     * 优先级, 值越小, 优先级越高
     *
     * @return
     */
    int order() default LOWEST_ORDER ;

    int LOWEST_ORDER = Integer.MAX_VALUE;
    int HIGHEST_ORDER = Integer.MIN_VALUE;
}

package com.wgs.inspire.chain.annotation;

import java.lang.annotation.*;

/**
 * Created by wanggenshen
 * Date: on 2019/11/20 22:53.
 * Description: 拦截器顺序
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface InterceptorOrder {

    /**
     * 值越低,优先级越高
     *
     * @return
     */
    int order() default LOWEST_ORDER;

    int LOWEST_ORDER = Integer.MAX_VALUE;
    int HIGHEST_ORDER = Integer.MIN_VALUE;
}

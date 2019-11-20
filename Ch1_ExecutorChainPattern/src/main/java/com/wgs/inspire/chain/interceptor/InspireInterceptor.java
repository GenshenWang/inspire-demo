package com.wgs.inspire.chain.interceptor;

import com.wgs.inspire.chain.model.InspireContext;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by wanggenshen
 * Date: on 2019/11/20 22:56.
 * Description: 拦截器抽象类
 */
public abstract class InspireInterceptor {

    @Getter
    @Setter
    private int order;

    /**
     * 执行完目标方法之前进行拦截
     */
    public boolean executeBefore(InspireContext context) {
        return true;
    }

    /**
     * 执行完目标方法之后进行拦截
     */
    public void executeAfter(InspireContext context) {

    }
}

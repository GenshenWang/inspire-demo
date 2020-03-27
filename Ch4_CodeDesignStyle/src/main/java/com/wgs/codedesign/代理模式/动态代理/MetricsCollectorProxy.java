package com.wgs.codedesign.代理模式.动态代理;

import com.wgs.codedesign.代理模式.MetricService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: wanggenshen
 * @date: 2020/3/27 22:18.
 * @description: XXX
 */
public class MetricsCollectorProxy {
    private MetricService metricService;

    public MetricsCollectorProxy() {
        this.metricService = new MetricService();
    }

    public Object createProxy(Object proxiedObj) {
        Class<?>[] interfaces = proxiedObj.getClass().getInterfaces();
        DynamicProxyHandler handler = new DynamicProxyHandler(proxiedObj);
        return Proxy.newProxyInstance(proxiedObj.getClass().getClassLoader(), interfaces, handler);
    }

    private class DynamicProxyHandler implements InvocationHandler {

        private Object proxiedObj;

        public DynamicProxyHandler(Object proxiedObj) {
            this.proxiedObj = proxiedObj;
        }

        @Override
        public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
            long startTime = System.currentTimeMillis();

            Object result = method.invoke(proxiedObj, args);

            long responseTime = System.currentTimeMillis() - startTime;
            metricService.recordRequest(method.getName(), responseTime);
            return result;
        }
    }



}

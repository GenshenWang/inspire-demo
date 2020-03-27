package com.wgs.codedesign.代理模式.静态代理;

import com.wgs.codedesign.代理模式.MetricService;
import com.wgs.codedesign.代理模式.UserController;

/**
 * @author: wanggenshen
 * @date: 2020/3/27 21:53.
 * @description: 静态代理实现方式二: 基于类的方式-继承
 */
public class UserControllerProxy extends UserController {

    private MetricService metricService;

    public UserControllerProxy(MetricService metricService) {
        this.metricService = metricService;
    }

    @Override
    public String login(String telephone, String password) {

        long startTime = System.currentTimeMillis();

        String res = super.login(telephone, password);

        long responseTime = System.currentTimeMillis() - startTime;
        metricService.recordRequest("login", responseTime);

        return res;
    }

    @Override
    public String register(String telephone, String password) {

        long startTime = System.currentTimeMillis();

        String res = super.register(telephone, password);

        long responseTime = System.currentTimeMillis() - startTime;
        metricService.recordRequest("register", responseTime);

        return res;
    }

    public static void main(String[] args) {
        MetricService metricService = new MetricService();
        String res = new UserControllerProxy(metricService).login("123", "456");
        System.out.println(res);
    }

}

package com.wgs.codedesign.代理模式.静态代理;

import com.wgs.codedesign.代理模式.MetricService;

/**
 * @author: wanggenshen
 * @date: 2020/3/27 22:02.
 * @description: 静态代理实现方式一: 基于接口的方式
 */
public class IUserControllerProxy implements IUserController {

    private MetricService metricService;
    private UserController userController;

    public IUserControllerProxy(UserController userController) {
        this.metricService = new MetricService();
        this.userController = userController;
    }

    @Override
    public String login(String telephone, String password) {

        long startTime = System.currentTimeMillis();

        String res = userController.login(telephone, password);

        long responseTime = System.currentTimeMillis() - startTime;
        metricService.recordRequest("login", responseTime);
        return res;
    }

    @Override
    public String register(String telephone, String password) {
        long startTime = System.currentTimeMillis();

        // 委托
        String res = userController.login(telephone, password);

        long responseTime = System.currentTimeMillis() - startTime;
        metricService.recordRequest("register", responseTime);
        return res;
    }

    public static void main(String[] args) {
        IUserController iUserController = new IUserControllerProxy(new UserController());
        String res = iUserController.login("123", "456");
        System.out.println(res);
    }
}

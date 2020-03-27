package com.wgs.codedesign.代理模式.动态代理;

/**
 * @author: wanggenshen
 * @date: 2020/3/28 00:05.
 * @description: XXX
 */
public class ProxyTest {

    public static void main(String[] args) {

        MetricsCollectorProxy proxy = new MetricsCollectorProxy();
        IUserController iUserController = (IUserController) proxy.createProxy(new UserController());
        iUserController.login("123", "456");

    }
}

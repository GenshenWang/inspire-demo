package com.wgs.codedesign.代理模式;

/**
 * @author: wanggenshen
 * @date: 2020/3/27 21:45.
 * @description: XXX
 */
public class UserController {

    public String login(String telephone, String password) {

        System.out.println("login success: " + telephone + ", " + password);
        return "login success";
    }

    public String register(String telephone, String password) {

        System.out.println("register success: " + telephone + ", " + password);
        return "register success";
    }
}

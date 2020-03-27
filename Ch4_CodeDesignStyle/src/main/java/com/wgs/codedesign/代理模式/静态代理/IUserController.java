package com.wgs.codedesign.代理模式.静态代理;

/**
 * @author: wanggenshen
 * @date: 2020/3/27 22:02.
 * @description: XXX
 */
public interface IUserController {

    String login(String telephone, String password);

    String register(String telephone, String password);
}

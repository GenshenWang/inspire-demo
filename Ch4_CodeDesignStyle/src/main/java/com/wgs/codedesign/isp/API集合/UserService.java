package com.wgs.codedesign.isp.API集合;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/18 00:47.
 * @description: XXX
 */
public interface UserService {

    boolean register(String userName, String password);
    void login(String userName, String password);
    boolean logout(String userName, String password);
}

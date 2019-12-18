package com.wgs.codedesign.isp.API集合.impl;

import com.wgs.codedesign.isp.API集合.UserDeleteService;
import com.wgs.codedesign.isp.API集合.UserService;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/18 00:50.
 * @description: XXX
 */
public class UserServiceImpl implements UserDeleteService, UserService {
    @Override
    public boolean deleteUnvalidUser(String userName) {
        return false;
    }

    @Override
    public boolean register(String userName, String password) {
        return false;
    }

    @Override
    public void login(String userName, String password) {

    }

    @Override
    public boolean logout(String userName, String password) {
        return false;
    }
}

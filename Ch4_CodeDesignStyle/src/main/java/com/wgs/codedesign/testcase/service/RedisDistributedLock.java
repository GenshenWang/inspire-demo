package com.wgs.codedesign.testcase.service;

/**
 * @author: Created by wanggenshen
 * @date: 2020/1/10 17:00.
 * @description: XXX
 */
public class RedisDistributedLock {

    private static final RedisDistributedLock INSTANCE = new RedisDistributedLock();

    public boolean lockTransaction(String transactionId) {
        return true;
    }

    public void unlockTransaction(String transactionId) {
        System.out.println("unlock success");
    }

    private RedisDistributedLock() {}


    public static RedisDistributedLock getSingletonInstance() {
        return INSTANCE;
    }
}

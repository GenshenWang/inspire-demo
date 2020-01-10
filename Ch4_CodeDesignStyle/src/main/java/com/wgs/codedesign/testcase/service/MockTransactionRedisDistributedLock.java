package com.wgs.codedesign.testcase.service;

/**
 * @author: Created by wanggenshen
 * @date: 2020/1/10 19:52.
 * @description: XXX
 */
public class MockTransactionRedisDistributedLock {

    public boolean lock(String id) {
        return RedisDistributedLock.getSingletonInstance().lockTransaction(id);
    }

    public void unlock(String id) {
        RedisDistributedLock.getSingletonInstance().unlockTransaction(id);
    }
}

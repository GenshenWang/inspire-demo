package com.wgs.auth.service.impl;

import com.wgs.auth.service.CredentialStorage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanggenshen
 * Date: on 2019/12/5 23:47.
 * Description: 密钥获取默认实现类, 存储方式为内存
 */
@Component("defaultCredentialStorage")
public class DefaultCredentialStorageImpl implements CredentialStorage {

    private static final Map<String, String> map = new HashMap<>();

    // mock appKey and password relations
    static {
        map.put("order", "order123");
        map.put("pay", "pay123");
        map.put("goods", "goods123");
        map.put("user", "user123");
    }

    @Override
    public String getPasswordByAppkey(String appKey) {
        return map.get(appKey);
    }
}

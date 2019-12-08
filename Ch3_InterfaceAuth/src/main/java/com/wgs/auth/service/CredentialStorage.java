package com.wgs.auth.service;

/**
 * Created by wanggenshen
 * Date: on 2019/12/5 23:46.
 * Description: 调用方的密钥信息存储类
 */
public interface CredentialStorage {

    /**
     * 获取密钥
     *
     * @param appKey 调用方身份标识
     * @return
     */
    String getPasswordByAppkey(String appKey);
}

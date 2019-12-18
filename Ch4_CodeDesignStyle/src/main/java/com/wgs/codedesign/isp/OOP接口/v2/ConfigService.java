package com.wgs.codedesign.isp.OOP接口.v2;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/18 00:57.
 * @description: 模拟数据中心, 如Nacos或ZK
 */
public interface ConfigService {

    String[] loadConfig(String key);

    void updateConfig(String key);
}

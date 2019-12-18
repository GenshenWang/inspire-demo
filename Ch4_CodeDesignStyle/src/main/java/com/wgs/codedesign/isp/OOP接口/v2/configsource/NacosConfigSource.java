package com.wgs.codedesign.isp.OOP接口.v2.configsource;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/18 10:50.
 * @description: 模拟Nacos功能
 */
public class NacosConfigSource implements ConfigSource {

    @Override
    public String[] loadConfig(String key) {
        return new String[0];
    }

    @Override
    public void updateConfig(String key) {

    }
}

package com.wgs.codedesign.isp.OOP接口.v1.configsource;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/18 10:54.
 * @description: XXX
 */
public interface ConfigSource {
    String[] loadConfig(String key);

    void updateConfig(String key);
}

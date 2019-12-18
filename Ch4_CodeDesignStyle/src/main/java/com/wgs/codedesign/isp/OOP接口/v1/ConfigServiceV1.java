package com.wgs.codedesign.isp.OOP接口.v1;

import java.util.Map;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/18 10:15.
 * @description: XXX
 */
public interface ConfigServiceV1 {
    /**
     * 从配置中心加载配置
     */
    void update();

    /**
     * 输出配置
     *
     * @param key
     * @return
     */
    String[] getConfig(String key);
}

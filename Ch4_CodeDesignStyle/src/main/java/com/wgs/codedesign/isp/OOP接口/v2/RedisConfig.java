package com.wgs.codedesign.isp.OOP接口.v2;

import com.wgs.codedesign.isp.OOP接口.v2.configsource.ConfigSource;
import lombok.Data;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/18 00:57.
 * @description: XXX
 */
@Data
public class RedisConfig implements ConfigUpdater, ConfigViewer{

    // mock ZK or Nacos.. service
    private ConfigSource configSource;
    private String address;
    private int timeout;
    private int maxTotal;

    // autowired
    public RedisConfig(ConfigSource configSource) {
        this.configSource = configSource;
    }



    @Override
    public void update() {
        // 模拟从config中读取数据并更新Redis本地变量信息
        String[] value = configSource.loadConfig("redis");
        this.address = value[0];
        this.timeout = Integer.parseInt(value[1]);
        this.maxTotal = Integer.parseInt(value[1]);
    }

    @Override
    public String getConfig(String config) {
        return "redis";
    }
}

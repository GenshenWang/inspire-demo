package com.wgs.codedesign.isp.OOP接口.v1;

import com.wgs.codedesign.isp.OOP接口.v1.configsource.ConfigSource;
import lombok.Data;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/18 00:57.
 * @description: XXX
 */
@Data
public class RedisConfig implements ConfigServiceV1 {

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
        this.maxTotal = Integer.parseInt(value[2]);
    }

    /**
     * RedisConfig只做更新, 不会获取, 所以返回空(实际上不应该实现 该接口)
     * @param key
     * @return
     */
    @Override
    public String[] getConfig(String key) {
        return null;
    }
}

package com.wgs.codedesign.isp.OOP接口.v1;

import com.wgs.codedesign.isp.OOP接口.v1.configsource.ConfigSource;
import com.wgs.codedesign.isp.OOP接口.v1.configsource.NacosConfigSource;
import lombok.Data;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/18 00:57.
 * @description: XXX
 */
@Data
public class KafkaConfig implements ConfigServiceV1 {

    // mock ZK or Nacos.. service
    private ConfigSource configSource;
    private String address;
    private int timeout;
    private int maxTotal;


    public KafkaConfig(ConfigSource configSource) {
        this.configSource = configSource;
    }

    @Override
    public void update() {
        // 模拟从config中读取数据并更新Kafka本地变量信息
        String[] value = configSource.loadConfig("kafka");
        this.address = value[0];
        this.timeout = Integer.parseInt(value[1]);
        this.maxTotal = Integer.parseInt(value[2]);
        // update
    }

    @Override
    public String[] getConfig(String key) {
        return null;
    }
}

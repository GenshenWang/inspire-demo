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
public class MysqlConfig implements ConfigServiceV1 {

    private String address;
    private int timeout;
    private int maxTotal;
    private ConfigSource configSource;

    // autowired
    public MysqlConfig(ConfigSource configSource) {
        this.configSource = configSource;
    }


    @Override
    public void update() {
    }

    @Override
    public String[] getConfig(String key) {
        return configSource.loadConfig("mysql");
    }
}

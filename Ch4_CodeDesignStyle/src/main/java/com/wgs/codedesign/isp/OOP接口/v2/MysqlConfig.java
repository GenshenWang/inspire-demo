package com.wgs.codedesign.isp.OOP接口.v2;

import com.wgs.codedesign.isp.OOP接口.v2.configsource.ConfigSource;
import lombok.Data;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/18 00:57.
 * @description: XXX
 */
@Data
public class MysqlConfig implements ConfigUpdater {

    // mock ZK or Nacos.. service
    private ConfigSource configSource;
    private String address;
    private int timeout;
    private int maxTotal;

    // autowired
    public MysqlConfig(ConfigSource configSource) {
        this.configSource = configSource;
    }


    @Override
    public void update() {
        System.out.println("mysql update");
    }
}

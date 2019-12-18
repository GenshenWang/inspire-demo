package com.wgs.codedesign.isp.OOP接口.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/18 11:04.
 * @description: XXX
 */
public class SimpleHttpServer {

    private String url;
    private int port;
    private Map<String, List<ConfigServiceV1>> configList = new HashMap<>(8);

    public SimpleHttpServer(int port, String url) {
        this.port = port;
        this.url = url;
    }


    public void addConfigView(String url, ConfigServiceV1 config) {

        if (!configList.containsKey(url)) {
            configList.put(url, new ArrayList<ConfigServiceV1>());
        }
        configList.get(url).add(config);
    }


    public void run() {
        // 模拟获取Config中的数据, 返回json信息
    }
}

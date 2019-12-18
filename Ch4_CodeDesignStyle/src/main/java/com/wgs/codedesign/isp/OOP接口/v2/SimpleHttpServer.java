package com.wgs.codedesign.isp.OOP接口.v2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/18 14:53.
 * @description: XXX
 */
public class SimpleHttpServer {
    private String host;
    private int port;
    // url - viewers
    private Map<String, List<ConfigViewer>> configViewerMap = new HashMap<>();

    public SimpleHttpServer(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void addViewer(String url, ConfigViewer viewer) {
        if (!configViewerMap.containsKey(url)) {
            configViewerMap.put(url, new ArrayList<ConfigViewer>());
        }
        configViewerMap.get(url).add(viewer);
    }

    public void run() {
        // show
    }
}

package com.wgs.codedesign.代理模式;

/**
 * @author: wanggenshen
 * @date: 2020/3/27 21:55.
 * @description: XXX
 */
public class MetricService {

    public void recordRequest(String method, long time) {

        System.out.println("method: " + method + " cost time: " + time);
    }
}

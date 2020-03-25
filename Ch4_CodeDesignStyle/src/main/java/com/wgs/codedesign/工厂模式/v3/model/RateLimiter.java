package com.wgs.codedesign.工厂模式.v3.model;

/**
 * @author: wanggenshen
 * @date: 2020/3/24 16:57.
 * @description: XXX
 */
public class RateLimiter {
    public RateConfig getRateConfig() {
        return rateConfig;
    }

    private RateConfig rateConfig;

    public RateLimiter(RateConfig rateConfig) {
        this.rateConfig = rateConfig;
    }

    public void rateLimit() {
        System.out.println("success");
    }

}

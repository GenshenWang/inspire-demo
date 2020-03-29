package com.wgs.codedesign.桥接模式;

/**
 * @author: wanggenshen
 * @date: 2020/3/29 00:32.
 * @description: XXX
 */
public class ApiAlertRule {
    private long maxQps;
    private long maxErrorLimit;

    public ApiAlertRule(long maxQps, long maxErrorLimit) {
        this.maxQps = maxQps;
        this.maxErrorLimit = maxErrorLimit;
    }


    public long getMaxQps() {
        return maxQps;
    }

    public long getMaxErrorLimit() {
        return maxErrorLimit;
    }

    public static ApiAlertRule matchRule(String api) {

        if ("getOrder".equals(api)) {
            return new ApiAlertRule(50, 50);
        } else if ("createOrder".equals(api)) {
            return new ApiAlertRule(60, 60);
        } else if ("dropOrder".equals(api)) {
            return new ApiAlertRule(70, 70);
        }
        return new ApiAlertRule(200, 300);

    }
}

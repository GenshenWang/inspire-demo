package com.wgs.sentinel.v1.rule;

import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/6/22 23:43.
 * @description: App应用限流规则
 */
public class AppRuleConfig {
    private String appId;
    private List<ApiLimit> limits;

    public AppRuleConfig() {
    }

    public AppRuleConfig(String appId, List<ApiLimit> apiLimitList) {
        this.appId = appId;
        this.limits = apiLimitList;
    }

    public String getAppId() {
        return appId;
    }

    public List<ApiLimit> getLimits() {
        return limits;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setLimits(List<ApiLimit> limits) {
        this.limits = limits;
    }
}

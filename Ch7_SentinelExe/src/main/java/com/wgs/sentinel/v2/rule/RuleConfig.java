package com.wgs.sentinel.v2.rule;

import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/6/22 23:44.
 * @description: RuleConfig
 *                  - AppRuleConfig
 *                      -  ApiLimit

configs:          对应RuleConfig
    appId: app-1    对应AppRuleConfig
    limits:
        api: /v1/user   对应ApiLimit
        limit: 100
        unit：60
        api: /v1/order
        limit: 50
    appId: app-2
    limits:
        api: /v1/user
        limit: 50
        api: /v1/order
        limit: 50
 *
 */
public class RuleConfig {
    private List<AppRuleConfig> configs;


    public RuleConfig() {
    }

    public RuleConfig(List<AppRuleConfig> configs) {
        this.configs = configs;
    }

    public List<AppRuleConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<AppRuleConfig> configs) {
        this.configs = configs;
    }
}

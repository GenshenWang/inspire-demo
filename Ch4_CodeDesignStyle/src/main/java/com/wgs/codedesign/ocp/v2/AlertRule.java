package com.wgs.codedesign.ocp.v2;

import lombok.Getter;

/**
 * Created by wanggenshen
 * Date: on 2019/12/11 09:42.
 * Description: 存储告警规则
 */
@Getter
public class AlertRule {
    private long maxTps;
    private long maxErrorLimit;

    public AlertRule(long maxTps, long maxErrorLimit) {
        this.maxTps = maxTps;
        this.maxErrorLimit = maxErrorLimit;
    }

    public static AlertRule getMatchedRule(String api) {
        // 模拟 "getOrder" 接口设置的最大tps和errorLimit, 设置的参数可以放到数据库或缓存
        if ("getOrder".equals(api)) {
            AlertRule orderAlertRule = new AlertRule(1000, 10);
            return orderAlertRule;
        } else if ("getUser".equals(api)) {
            AlertRule userAlertRule = new AlertRule(1500, 15);
            return userAlertRule;
        } else {
            AlertRule commonAlertRule = new AlertRule(500, 20);
            return commonAlertRule;
        }
    }
}

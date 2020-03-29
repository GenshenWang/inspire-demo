package com.wgs.codedesign.桥接模式.v1.handler;

import com.wgs.codedesign.桥接模式.ApiAlertRule;
import com.wgs.codedesign.桥接模式.model.ApiStatInfo;
import com.wgs.codedesign.桥接模式.v1.Notification;

/**
 * @author: wanggenshen
 * @date: 2020/3/29 00:36.
 * @description: XXX
 */
public abstract class ApiAlertHandler {

    protected ApiAlertRule apiAlertRule;
    protected Notification notification;

    public ApiAlertHandler(ApiAlertRule apiAlertRule, Notification notification) {
        this.apiAlertRule = apiAlertRule;
        this.notification = notification;
    }

    public ApiAlertRule getMatchedRule(String api) {
        return ApiAlertRule.matchRule(api);
    }

    public abstract void check(ApiStatInfo apiStatInfo);

}

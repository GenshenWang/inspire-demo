package com.wgs.codedesign.桥接模式.v2.handler;

import com.wgs.codedesign.桥接模式.ApiAlertRule;
import com.wgs.codedesign.桥接模式.model.ApiStatInfo;
import com.wgs.codedesign.桥接模式.model.NotificationEmergencyLevel;
import com.wgs.codedesign.桥接模式.v2.notification.NotificationV2;

/**
 * @author: wanggenshen
 * @date: 2020/3/29 00:30.
 * @description: XXX
 */
public class ErrorAlertHandler extends ApiAlertHandler {


    public ErrorAlertHandler(ApiAlertRule apiAlertRule, NotificationV2 notification) {
        super(apiAlertRule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        ApiAlertRule rule = getMatchedRule(apiStatInfo.getApi());
        if (apiStatInfo.getErrorCount() > rule.getMaxErrorLimit()) {
            notification.notify("api error count exceed limit " + apiStatInfo.getApi());
        }
    }
}

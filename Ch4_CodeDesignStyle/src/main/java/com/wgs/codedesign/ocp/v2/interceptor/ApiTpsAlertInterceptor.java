package com.wgs.codedesign.ocp.v2.interceptor;

import com.wgs.codedesign.ocp.v1.NotificationEmergencyLevelEnum;
import com.wgs.codedesign.ocp.v2.interceptor.constant.AlertConstant;
import com.wgs.codedesign.ocp.v2.AlertRule;
import com.wgs.codedesign.ocp.v2.ApiStatInfo;
import com.wgs.codedesign.ocp.v2.Notification;
import com.wgs.codedesign.ocp.v2.interceptor.annotation.InterceptorOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wanggenshen
 * Date: on 2019/12/11 10:31.
 * Description: XXX
 */
@Component
@InterceptorOrder(order = 10)
public class ApiTpsAlertInterceptor extends ApiAlertInterceptor {

    @Autowired
    private Notification notification;


    @Override
    public void handler(ApiStatInfo apiStatInfo) {
        if (apiStatInfo == null) {
            return;
        }
        String api = apiStatInfo.getApi();
        AlertRule alertRule = getRule(api);
        long durationSeconds = apiStatInfo.getDurationSeconds();
        long tps = durationSeconds == 0 ? 0 : apiStatInfo.getRequestCount() / durationSeconds;
        if (tps > alertRule.getMaxTps()) {
            String notifyMsg = String.format(AlertConstant.NOTIFY_MSG, "URGENCY", api);
            notification.notify(NotificationEmergencyLevelEnum.URGENCY.getCode(), notifyMsg);
        }
    }
}

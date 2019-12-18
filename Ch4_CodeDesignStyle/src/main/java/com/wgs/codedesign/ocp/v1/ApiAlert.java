package com.wgs.codedesign.ocp.v1;

/**
 * Created by wanggenshen
 * Date: on 2019/12/11 09:55.
 * Description: API 接口监控
 */
public class ApiAlert {
    private AlertRule alertRule;
    private Notification notification;

    private static final String NOTIFY_MSG = "【%s】api:[%s] tps exceed max tps";

    public ApiAlert(AlertRule alertRule, Notification notification) {
        this.alertRule = alertRule;
        this.notification = notification;
    }

    /**
     * 是否需要发送告警
     *
     * @param api               接口名
     * @param requestCount      接口调用量
     * @param errorCount        接口调用失败次数
     * @param durationSeconds   窗口期
     */
    public void check(String api, long requestCount, long errorCount, long durationSeconds) {
        AlertRule alertRule = AlertRule.getMatchedRule(api);
        // calculate tps, to evaluate if need to send URGENCY notify
        long tps = requestCount / durationSeconds;
        if (tps > alertRule.getMaxTps()) {
            String notifyMsg = String.format(NOTIFY_MSG, "URGENCY", api);
            notification.notify(NotificationEmergencyLevelEnum.URGENCY.getCode(), notifyMsg);
        }

        // calculate errorCount, to evaluate if need to send URGENCY notify
        if (errorCount > alertRule.getMaxErrorLimit()) {
            String notifyMsg = String.format(NOTIFY_MSG, "SEVERE", api);
            notification.notify(NotificationEmergencyLevelEnum.SEVERE.getCode(), notifyMsg);
        }

    }


}

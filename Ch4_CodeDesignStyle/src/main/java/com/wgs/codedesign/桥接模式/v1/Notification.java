package com.wgs.codedesign.桥接模式.v1;

import com.wgs.codedesign.桥接模式.model.NotificationEmergencyLevel;

import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/3/29 00:09.
 * @description: 类似于NotifyService
 */
public class Notification {
    // 发邮件
    private List<String> emailAddresses;
    // 打电话
    private List<String> telephones;
    // 微信
    private List<String> wechatIds;

    public Notification() {
    }

    public void setEmailAddresses(List<String> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public void setTelephones(List<String> telephones) {
        this.telephones = telephones;
    }

    public void setWechatIds(List<String> wechatIds) {
        this.wechatIds = wechatIds;
    }

    public void notify(NotificationEmergencyLevel emergencyLevel, String message) {
        if (NotificationEmergencyLevel.SEVERE.equals(emergencyLevel)) {
            System.out.println("serve: " + message);
        } else if (NotificationEmergencyLevel.URGENCY.equals(emergencyLevel)) {
            System.out.println("urgency:" + message);
        } else if (NotificationEmergencyLevel.NORMAL.equals(emergencyLevel)) {
            System.out.println("normal: " + emergencyLevel);
        } else if (NotificationEmergencyLevel.TRIVIAL.equals(emergencyLevel)) {
            System.out.println("TRIVIAL: " + emergencyLevel);
        }

    }
}

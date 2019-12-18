package com.wgs.codedesign.ocp.v1;

import lombok.Getter;

/**
 * Created by wanggenshen
 * Date: on 2019/12/11 09:48.
 * Description: 告警严重程度
 */
@Getter
public enum NotificationEmergencyLevelEnum {
    SEVERE(0, "严重"),
    URGENCY(1, "紧急"),
    NORMAL(2, "普通"),
    TRIVIAL(3, "无关紧要")

    ;

    private int code;
    private String desc;

    NotificationEmergencyLevelEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}

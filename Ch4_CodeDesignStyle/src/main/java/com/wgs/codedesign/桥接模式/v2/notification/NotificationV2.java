package com.wgs.codedesign.桥接模式.v2.notification;

import com.wgs.codedesign.桥接模式.model.NotificationEmergencyLevel;
import com.wgs.codedesign.桥接模式.v2.msg.MsgSender;

import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/3/29 00:09.
 * @description: 类似于NotifyService
 */
public abstract class NotificationV2 {

    protected MsgSender msgSender;

    public NotificationV2(MsgSender msgSender) {
        this.msgSender = msgSender;
    }

    public abstract void notify(String message);
}

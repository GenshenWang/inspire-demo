package com.wgs.codedesign.桥接模式.v2.notification;

import com.wgs.codedesign.桥接模式.v2.msg.MsgSender;

/**
 * @author: wanggenshen
 * @date: 2020/3/29 14:57.
 * @description: XXX
 */
public class UrgencyNotification extends NotificationV2 {


    public UrgencyNotification(MsgSender msgSender) {
        super(msgSender);
    }

    @Override
    public void notify(String message) {
        msgSender.send(message);
    }
}

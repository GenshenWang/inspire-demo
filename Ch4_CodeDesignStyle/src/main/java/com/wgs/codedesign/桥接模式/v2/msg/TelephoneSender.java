package com.wgs.codedesign.桥接模式.v2.msg;

import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/3/29 14:53.
 * @description: XXX
 */
public class TelephoneSender implements MsgSender {

    private List<String> telephones;

    public TelephoneSender(List<String> telephones) {
        this.telephones = telephones;
    }

    @Override
    public void send(String message) {
        System.out.println("开始打电话了:" + telephones + ", 消息是: " + message);
    }
}

package com.wgs.codedesign.桥接模式.v2.msg;

import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/3/29 14:53.
 * @description: XXX
 */
public class EmailSender implements MsgSender {

    private List<String> emails;

    public EmailSender(List<String> telephones) {
        this.emails = telephones;
    }

    @Override
    public void send(String message) {
        System.out.println("开始发邮件了:" + emails + ", 消息是: " + message);
    }
}

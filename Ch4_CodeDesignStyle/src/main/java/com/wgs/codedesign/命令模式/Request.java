package com.wgs.codedesign.命令模式;

/**
 * @author: wanggenshen
 * @date: 2020/5/3 16:09.
 * @description: XXX
 */
public class Request {
    private Event type;
    private String data;

    public Request(Event type, String data) {
        this.type = type;
        this.data = data;
    }

    public Event getType() {
        return type;
    }

    public String getData() {
        return data;
    }
}

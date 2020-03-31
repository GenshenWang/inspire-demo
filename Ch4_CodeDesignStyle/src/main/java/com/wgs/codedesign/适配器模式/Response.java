package com.wgs.codedesign.适配器模式;

/**
 * @author: wanggenshen
 * @date: 2020/3/31 21:48.
 * @description: XXX
 */
public class Response {
    private int code;
    private boolean success;

    public Response(int code, boolean success) {
        this.code = code;
        this.success = success;
    }

    public static Response success() {
        return new Response(200, true);
    }

    public static Response fail() {
        return new Response(400, false);
    }
}

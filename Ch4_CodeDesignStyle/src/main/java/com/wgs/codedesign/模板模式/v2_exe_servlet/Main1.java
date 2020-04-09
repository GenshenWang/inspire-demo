package com.wgs.codedesign.模板模式.v2_exe_servlet;

/**
 * @author: wanggenshen
 * @date: 2020/4/9 20:08.
 * @description: XXX
 */
public class Main1 {

    public static void main(String[] args) {
        MyHttpServlet servlet = new HelloServlet();
        servlet.service("GET", "RESPONSE");

        MyHttpServlet servlet2 = new HelloServlet2();
        servlet2.service("GET", "RESPONSE");
    }
}

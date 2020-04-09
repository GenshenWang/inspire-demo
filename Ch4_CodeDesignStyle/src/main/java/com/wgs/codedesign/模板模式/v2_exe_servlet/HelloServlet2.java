package com.wgs.codedesign.模板模式.v2_exe_servlet;

/**
 * @author: wanggenshen
 * @date: 2020/4/9 20:07.
 * @description: XXX
 */
public class HelloServlet2 extends MyHttpServlet {


    @Override
    protected void doGet(String req, String resp) {
        System.out.println("HelloServlet2 执行doGet");
    }

    @Override
    protected void doPost(String req, String resp) {
        System.out.println("HelloServlet2 执行doPost");
    }
}

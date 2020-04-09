package com.wgs.codedesign.模板模式.v2_exe_servlet;


/**
 * @author: wanggenshen
 * @date: 2020/4/9 19:59.
 * @description: 模拟HttpServlet, 通过模板方法定义service
 */
public abstract class MyHttpServlet {

    /**
     * 模板方法, 定义service方法;
     * doGet、doPost由子类去实现
     *
     * @param req
     * @param resp
     */
    public void service(String req, String resp) {
        // 校验req和resp
        System.out.println("req 和 resp: " + req );

        if ("GET".equals(req)) {
            System.out.println("execute get method");
            doGet(req, resp);
        } else if ("POST".equals(req)) {
            System.out.println("execute post method");
            doPost(req, resp);
        }
    }

    protected void doGet(String req, String resp) {
        // 做个初步的校验
        System.out.println("父类MyHttpServlet初步的doGet校验");

        // 剩下的由自己实现的Servlet类去执行
    }

    protected void doPost(String req, String resp) {
        // 做个初步的校验
        System.out.println("父类MyHttpServlet初步的doPost校验");

        // 剩下的由自己实现的Servlet类去执行
    }
}

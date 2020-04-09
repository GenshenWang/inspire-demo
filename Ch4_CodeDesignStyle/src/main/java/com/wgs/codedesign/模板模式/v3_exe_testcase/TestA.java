package com.wgs.codedesign.模板模式.v3_exe_testcase;

/**
 * @author: wanggenshen
 * @date: 2020/4/9 22:41.
 * @description: XXX
 */
public class TestA extends AbstractTestCase {

    @Override
    protected String executeCase() {
        System.out.println("TestA执行完毕");
        return "success";
    }

    @Override
    protected void tearDown() {
        System.out.println("TestA执行结束, 关闭MySQL连接");
    }

    @Override
    protected void setUp() {
        System.out.println("TestA开始执行了,MySQL初始化");
    }
}

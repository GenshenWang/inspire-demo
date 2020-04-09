package com.wgs.codedesign.模板模式.v3_exe_testcase;

/**
 * @author: wanggenshen
 * @date: 2020/4/9 22:39.
 * @description: XXX
 */
public abstract class AbstractTestCase {

    public void runTestCase() {

        setUp();

        String res = executeCase();

        tearDown();
    }

    protected abstract String executeCase();

    protected abstract void tearDown();

    protected abstract void setUp();
}

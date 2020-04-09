package com.wgs.codedesign.模板模式.v3_exe_testcase;

/**
 * @author: wanggenshen
 * @date: 2020/4/9 22:42.
 * @description: XXX
 */
public class Main3 {

    public static void main(String[] args) {
        AbstractTestCase testCase = new TestA();
        testCase.runTestCase();

        AbstractTestCase testCase2 = new TestB();
        testCase2.runTestCase();
    }
}

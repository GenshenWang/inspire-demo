package com.wgs.codedesign.testcase.service;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: Created by wanggenshen
 * @date: 2020/1/10 16:39.
 * @description: XXX
 */
public class IdGenerator {

    private static final AtomicLong adder = new AtomicLong();

    public static String generateTransactionId() {
        return String.valueOf(adder.decrementAndGet());
    }
}

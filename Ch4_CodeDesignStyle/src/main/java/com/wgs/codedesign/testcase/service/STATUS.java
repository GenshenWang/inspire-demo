package com.wgs.codedesign.testcase.service;

import lombok.Getter;

/**
 * @author: Created by wanggenshen
 * @date: 2020/1/10 16:46.
 * @description: XXX
 */
public enum  STATUS {
    UN_EXECUTED(0),
    EXECUTED(1),
    EXPIRED(2),
    FAILED(3);

    @Getter
    Integer code;

    STATUS(int code) {
        this.code = code;
    }
}

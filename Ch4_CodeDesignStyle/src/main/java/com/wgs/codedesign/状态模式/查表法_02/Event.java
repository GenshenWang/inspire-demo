package com.wgs.codedesign.状态模式.查表法_02;

/**
 * @author: wanggenshen
 * @date: 2020/4/17 14:09.
 * @description: XXX
 */
public enum  Event {
    OBTAIN_MUSHROOM(0),
    OBTAIN_CAPE(1),
    OBTAIN_FIRE(2),
    MEET_MONSTER(3)
    ;

    private int value;

    Event(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

package com.wgs.codedesign.状态模式.状态模式_03;

/**
 * @author: wanggenshen
 * @date: 2020/4/17 12:24.
 * @description: XXX
 */
public enum StateV3 {
    // 小马里奥
    SMALL(0),
    // 超级马里奥
    SUPER(1),
    // 斗篷马里奥
    CAPE(2),
    // 火焰马里奥
    FIRE(3),

    ;

    private int value;

    StateV3(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

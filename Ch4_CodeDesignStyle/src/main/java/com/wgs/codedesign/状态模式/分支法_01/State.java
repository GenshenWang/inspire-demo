package com.wgs.codedesign.状态模式.分支法_01;

/**
 * @author: wanggenshen
 * @date: 2020/4/17 12:24.
 * @description: XXX
 */
public enum State {
    // 小马里奥
    SMALL(0),
    // 超级马里奥
    SUPER(1),
    // 火焰马里奥
    FIRE(2),
    // 斗篷马里奥
    CAPE(3)

    ;

    private int value;

    State(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

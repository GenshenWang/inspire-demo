package com.wgs.codedesign.解释器模式.v2_解释器.expression;

/**
 * @author: wanggenshen
 * @date: 2020/5/4 00:28.
 * @description: XXX
 */
public class NumberExpression implements Expression {

    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    public NumberExpression(String number) {
        this.number = Integer.parseInt(number);
    }

    @Override
    public int interpret() {
        return this.number;
    }
}

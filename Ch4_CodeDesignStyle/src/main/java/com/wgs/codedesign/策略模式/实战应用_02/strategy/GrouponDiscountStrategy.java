package com.wgs.codedesign.策略模式.实战应用_02.strategy;

/**
 * @author: wanggenshen
 * @date: 2020/4/13 21:49.
 * @description: XXX
 */
public class GrouponDiscountStrategy implements DiscountStrategy {
    @Override
    public double calDiscount(double originMoney) {
        System.out.println("团购订单,打5折");
        return originMoney * 0.5;
    }
}

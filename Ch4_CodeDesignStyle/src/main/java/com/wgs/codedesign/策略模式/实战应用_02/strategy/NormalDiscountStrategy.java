package com.wgs.codedesign.策略模式.实战应用_02.strategy;

/**
 * @author: wanggenshen
 * @date: 2020/4/13 21:48.
 * @description: XXX
 */
public class NormalDiscountStrategy implements DiscountStrategy {
    @Override
    public double calDiscount(long orderId) {
        System.out.println("普通打折, 打9.2折扣");
        return orderId;
    }
}

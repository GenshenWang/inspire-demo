package com.wgs.codedesign.策略模式.实战应用_02.strategy;

/**
 * @author: wanggenshen
 * @date: 2020/4/13 21:49.
 * @description: XXX
 */
public class PromotionDiscountStrategy implements DiscountStrategy {
    @Override
    public double calDiscount(double originMoney) {
        System.out.println("促销订单, 一口价5元");
        return 5;
    }
}

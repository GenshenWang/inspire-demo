package com.wgs.codedesign.策略模式.实战应用_02;

import com.wgs.codedesign.策略模式.实战应用_02.strategy.DiscountStrategy;

/**
 * @author: wanggenshen
 * @date: 2020/4/13 23:22.
 * @description: XXX
 */
public class OrderServiceV2 {

    public double discount(String orderType, double originMoney) {
        DiscountStrategy discountStrategy = DiscountStrategyFactory.getDiscountStrategy(orderType);
        return discountStrategy.calDiscount(originMoney);
    }
}

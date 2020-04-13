package com.wgs.codedesign.策略模式.实战应用_02;

import com.wgs.codedesign.策略模式.实战应用_02.strategy.DiscountStrategy;

/**
 * @author: wanggenshen
 * @date: 2020/4/13 21:52.
 * @description: XXX
 */
public class Main2 {

    public static void main(String[] args) {

        String orderType = "promotion";
        double originMoney = 100;

        // 不适应策略模式
        OrderServiceV1 orderServiceV1 = new OrderServiceV1();
        orderServiceV1.discount(orderType, originMoney);

        // 使用策略模式
        DiscountStrategy discountStrategy = DiscountStrategyFactory.getDiscountStrategy(orderType);
        discountStrategy.calDiscount(originMoney);
    }
}

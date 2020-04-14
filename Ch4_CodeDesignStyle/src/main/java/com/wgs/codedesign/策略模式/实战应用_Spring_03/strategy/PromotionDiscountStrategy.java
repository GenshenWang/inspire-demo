package com.wgs.codedesign.策略模式.实战应用_Spring_03.strategy;

import org.springframework.stereotype.Service;

/**
 * @author: wanggenshen
 * @date: 2020/4/13 21:49.
 * @description: XXX
 */
@Service
@StrategyAnno(type = "promotion")
public class PromotionDiscountStrategy implements DiscountStrategy {
    @Override
    public double calDiscount(double originMoney) {
        System.out.println("促销订单, 一口价5元");
        return 5;
    }
}

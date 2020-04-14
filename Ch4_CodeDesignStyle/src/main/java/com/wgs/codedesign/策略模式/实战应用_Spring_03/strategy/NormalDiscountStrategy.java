package com.wgs.codedesign.策略模式.实战应用_Spring_03.strategy;

import org.springframework.stereotype.Service;

/**
 * @author: wanggenshen
 * @date: 2020/4/13 21:48.
 * @description: XXX
 */
@Service
@StrategyAnno(type = "normal")
public class NormalDiscountStrategy implements DiscountStrategy {
    @Override
    public double calDiscount(double originMoney) {
        System.out.println("普通打折, 打9.2折扣");
        return originMoney * 9.2;
    }
}

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

        DiscountStrategy discountStrategy = DiscountStrategyFactory.getDiscountStrategy(orderType);
        discountStrategy.calDiscount(3284204092019904839L);
    }
}

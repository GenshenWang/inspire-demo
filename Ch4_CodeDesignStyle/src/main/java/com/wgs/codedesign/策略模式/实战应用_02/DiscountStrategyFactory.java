package com.wgs.codedesign.策略模式.实战应用_02;

import com.wgs.codedesign.策略模式.实战应用_02.strategy.DiscountStrategy;
import com.wgs.codedesign.策略模式.实战应用_02.strategy.GrouponDiscountStrategy;
import com.wgs.codedesign.策略模式.实战应用_02.strategy.NormalDiscountStrategy;
import com.wgs.codedesign.策略模式.实战应用_02.strategy.PromotionDiscountStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wanggenshen
 * @date: 2020/4/13 21:50.
 * @description: 策略工厂类
 */
public class DiscountStrategyFactory {
    private static final Map<String, DiscountStrategy> strategies = new HashMap<>();

    static {
        strategies.put("normal", new NormalDiscountStrategy());
        strategies.put("group", new GrouponDiscountStrategy());
        strategies.put("promotion", new PromotionDiscountStrategy());
    }


    public static DiscountStrategy getDiscountStrategy(String type) {
        return strategies.get(type);
    }
}

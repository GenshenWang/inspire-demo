package com.wgs.codedesign.策略模式.实战应用_Spring_03;

import com.wgs.codedesign.策略模式.实战应用_Spring_03.strategy.DiscountStrategy;
import com.wgs.codedesign.策略模式.实战应用_Spring_03.strategy.StrategyAnno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wanggenshen
 * @date: 2020/4/13 23:39.
 * @description: 策略模式 - 创建
 */
@Component
public class DiscountStrategyContext {

    @Autowired
    private List<DiscountStrategy> strategyList;

    private volatile Map<String, DiscountStrategy> discountStrategyMap = new HashMap<>();

    @PostConstruct
    public void loadDiscountStrategy() {
        if (CollectionUtils.isEmpty(strategyList)) {
            throw new RuntimeException("Ioc autowired strategyList failed");
        }
        strategyList.forEach(discountStrategy -> {
            String type = resolveAnnotation(discountStrategy);
            discountStrategyMap.put(type, discountStrategy);
        });
    }

    private String resolveAnnotation(DiscountStrategy discountStrategy) {
        if (discountStrategy.getClass().isAnnotationPresent(StrategyAnno.class)) {
            return discountStrategy.getClass().getAnnotation(StrategyAnno.class).type();
        }
        throw new RuntimeException("DiscountStrategy has miss StrategyAnno: " + discountStrategy.getClass().getName());
    }

    public DiscountStrategy selectDiscountStrategy(String type) {
        return discountStrategyMap.get(type);
    }


}

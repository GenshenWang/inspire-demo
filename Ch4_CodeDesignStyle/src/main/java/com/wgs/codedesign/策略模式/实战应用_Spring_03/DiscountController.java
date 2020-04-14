package com.wgs.codedesign.策略模式.实战应用_Spring_03;

import com.wgs.codedesign.策略模式.实战应用_Spring_03.strategy.DiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: wanggenshen
 * @date: 2020/4/13 23:38.
 * @description: 策略模式使用示例
 */
@Controller
@RequestMapping("/order")
public class DiscountController {

    @Autowired
    private DiscountStrategyContext discountStrategyContext;


    /**
     * 策略模式使用示例
     *
     * @param type   type = "promotion" 或 "normal" 或 "group"
     * @param money
     */
    @RequestMapping("/discount")
    public void test(@RequestParam("type") String type, @RequestParam("money") double money) {
        DiscountStrategy discountStrategy = discountStrategyContext.selectDiscountStrategy(type);
        discountStrategy.calDiscount(money);
    }
}

package com.wgs.codedesign.策略模式.实战应用_02;


/**
 * @author: wanggenshen
 * @date: 2020/4/13 23:22.
 * @description: XXX
 */
public class OrderServiceV1 {

    public double discount(String orderType, double originMoney) {
        
        if ("normal".equals(orderType)) {
            System.out.println("普通打折, 打9.2折扣");
            return originMoney * 9.2;
        } else if ("group".equals(orderType)) {
            System.out.println("团购订单,打5折");
            return originMoney * 0.5;
        } else if ("promotion".equals(orderType)) {
            System.out.println("促销订单, 一口价5元");
            return 5;
        }
        return originMoney;
    }
}

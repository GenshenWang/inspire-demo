package com.wgs.codedesign.代理模式;

/**
 * @author: wanggenshen
 * @date: 2020/3/27 21:45.
 * @description: XXX
 */
public class OrderController {

    public String queryOrder(String orderId) {

        System.out.println("query order success" + orderId);
        return "query order success";
    }
}

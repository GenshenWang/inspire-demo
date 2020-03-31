package com.wgs.codedesign.适配器模式.exe1_封装外部接口;

/**
 * @author: wanggenshen
 * @date: 2020/3/31 21:35.
 * @description: 假设为外部接口 无法修改
 */
public class OrderService {

    public int createOrder(long userId, long orderId) {
        System.out.println("success");
        return 1;
    }

    public int refundOrder(long userId, long orderId) {
        System.out.println("success");
        return -1;
    }

}

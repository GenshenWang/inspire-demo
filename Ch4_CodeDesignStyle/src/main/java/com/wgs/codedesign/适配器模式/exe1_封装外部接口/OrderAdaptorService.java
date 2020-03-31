package com.wgs.codedesign.适配器模式.exe1_封装外部接口;

import com.wgs.codedesign.适配器模式.Response;

/**
 * @author: wanggenshen
 * @date: 2020/3/31 22:11.
 * @description: XXX
 */
public class OrderAdaptorService implements LocalOrderServcie {

    private OrderService orderService;

    @Override
    public Response createOrder(long userId, long orderId) {
        int res = orderService.createOrder(userId, orderId);
        if (res == 1) {
            return Response.success();
        } else {
            return Response.fail();
        }

    }

    @Override
    public Response refundOrder(long userId, long orderId) {
        int res = orderService.refundOrder(userId, orderId);
        if (res == 1) {
            return Response.success();
        } else {
            return Response.fail();
        }
    }
}

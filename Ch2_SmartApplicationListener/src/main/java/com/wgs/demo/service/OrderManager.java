package com.wgs.demo.service;

import com.wgs.demo.event.OrderCreateEvent;
import com.wgs.demo.event.OrderPayEvent;
import com.wgs.demo.model.OrderDTO;
import com.wgs.demo.publish.EventPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by wanggenshen
 * Date: on 2019/11/22 23:40.
 * Description: XXX
 */
@Component
public class OrderManager {

    @Autowired
    @Qualifier("springEventPublishService")
    private EventPublishService eventPublishService;

    /**
     * 模拟创建订单
     */
    public void createOrder(String userId) {
        // 1、生成订单号
        long orderId = 12345L;

        OrderDTO order = new OrderDTO();
        order.setOrderId(orderId);
        order.setCreateTime(new Date());

        // 2、调用第三方的接口, 去通知订单创建
        // invoke service...

        // 使用事件发布机制解耦
        eventPublishService.publishEvent(new OrderCreateEvent(this, order));

    }

    /**
     * 模拟订单支付
     */
    public void payOrder(String userId) {
        // 1、生成订单号
        long orderId = 12345L;

        OrderDTO order = new OrderDTO();
        order.setOrderId(orderId);
        order.setCreateTime(new Date());


        // 使用事件发布机制解耦
        eventPublishService.publishEvent(new OrderPayEvent(this, order));

    }
}

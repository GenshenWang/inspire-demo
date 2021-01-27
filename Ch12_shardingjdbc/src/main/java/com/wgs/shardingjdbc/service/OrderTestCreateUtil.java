package com.wgs.shardingjdbc.service;

import com.wgs.shardingjdbc.dao.OrderDO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;

@Component
public class OrderTestCreateUtil {

    private final Random random = new Random();

    @Resource
    private OrderService orderService;

    /**
     * 测试订单生成脚本工具
     *
     * 每个用户模拟生成500笔订单
     * （1）userId1 % 2 = 1，订单会进入order1库；
     *     生成的订单号为随机号，如果orderId % 2 = 0，进入t_order_0表；否则进入t_order_1表
     *
     *  (2) userId2生成的订单规则同上。
     */
    public void initOrder() {
        long userId1 = 111111L;
        long userId2 = 222222L;



        for (int i = 0; i < 500; i++) {
            // 生成用户1的订单
            OrderDO orderDO = new OrderDO();
            orderDO.setOrderId(createOrderId());
            orderDO.setUserId(userId1);
            orderDO.setMoney(random.nextInt(3));
            orderService.insert(orderDO);

            // 生成用户2的订单
            OrderDO orderDO2 = new OrderDO();
            orderDO2.setOrderId(createOrderId());
            orderDO2.setUserId(userId2);
            orderDO2.setMoney(random.nextInt(2));
            orderService.insert(orderDO2);
        }
    }

    private long createOrderId() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return Long.parseLong(sb.toString());
    }
}

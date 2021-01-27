package com.wgs.shardingjdbc.controller;

import com.alibaba.fastjson.JSON;
import com.wgs.shardingjdbc.dao.OrderDO;
import com.wgs.shardingjdbc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderTestController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/query")
    @ResponseBody
    public String queryOrders(@RequestParam("userId") long userId) {
        OrderDO param = new OrderDO();
        param.setUserId(userId);
        List<OrderDO> orderDOS = orderService.select(param);
        return JSON.toJSONString(orderDOS);
    }

    @GetMapping("/order/create")
    @ResponseBody
    public void createOrder(@RequestParam("orderId") long orderId,
                              @RequestParam("userId") long userId,
                              @RequestParam("money") int money) {
        OrderDO param = new OrderDO();
        param.setOrderId(orderId);
        param.setUserId(userId);
        param.setMoney(money);

        orderService.insert(param);
    }
}

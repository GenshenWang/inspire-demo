package com.wgs.shardingjdbc.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.wgs.shardingjdbc.dao.OrderDO;
import com.wgs.shardingjdbc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderTestController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/query")
    @ResponseBody
    public String queryOrders(@RequestParam("orderId") long orderId) {
        OrderDO param = new OrderDO();
        param.setOrderId(orderId);
        return JSONUtils.toJSONString(orderService.select(param).get(0));
    }

    @GetMapping("/order/create")
    @ResponseBody
    public void createOrder(@RequestParam("orderId") long orderId,
                              @RequestParam("userId") long userId,
                              @RequestParam("money") int money) {
        OrderDO param = new OrderDO();
        param.setOrderId(orderId);
        param.setUseId(userId);
        param.setMoney(money);

        orderService.insert(param);
    }
}

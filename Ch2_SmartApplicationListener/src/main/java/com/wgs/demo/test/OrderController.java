package com.wgs.demo.test;

import com.wgs.demo.service.OrderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by wanggenshen
 * Date: on 2019/11/22 23:58.
 * Description: XXX
 */
@RequestMapping("/order")
@Controller
public class OrderController {

    @Autowired
    private OrderManager orderManager;


    @RequestMapping("/create")
    @ResponseBody
    public void createOrder() {

        orderManager.createOrder("tom");
    }

    /**
     * 模拟事件异步操作
     */
    @RequestMapping("/pay")
    @ResponseBody
    public void payOrder() throws InterruptedException {
        orderManager.payOrder("tom");

        // 模拟异步发送
        System.out.println("主线程支付操作完成, 异步发送支付事件" + new Date());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

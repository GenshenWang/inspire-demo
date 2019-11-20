package com.wgs.inspire.chain.test;

import com.wgs.inspire.chain.rpc.service.RefundService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wanggenshen
 * Date: on 2019/11/20 23:47.
 * Description: XXX
 */
@Controller
@RequestMapping("order")
public class RefundController {

    @Autowired
    private RefundService refundService;

    @RequestMapping("/refund")
    public void refund(@Param("orderId") String orderId,
                       @Param("reason") String reason) {

        refundService.refund(orderId, reason);
    }
}

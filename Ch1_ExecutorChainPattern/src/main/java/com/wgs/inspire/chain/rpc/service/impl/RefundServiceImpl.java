package com.wgs.inspire.chain.rpc.service.impl;

import com.wgs.inspire.chain.rpc.RpcResult;
import com.wgs.inspire.chain.rpc.service.RefundService;
import com.wgs.inspire.chain.rpc.service.RpcResultBuilder;
import org.springframework.stereotype.Service;

/**
 * Created by wanggenshen
 * Date: on 2019/11/20 23:43.
 * Description: 测试类
 */
@Service("refundService")
public class RefundServiceImpl implements RefundService {
    @Override
    public RpcResult<Boolean> refund(String orderId, String reason) {
        // 模拟接口调用时长
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if ("1001".equals(orderId)) {
            return RpcResultBuilder.buildSuccess(true);
        } else {
            return RpcResultBuilder.buildFail(false);
        }


    }
}

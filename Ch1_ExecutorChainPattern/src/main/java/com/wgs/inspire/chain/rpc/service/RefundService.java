package com.wgs.inspire.chain.rpc.service;

import com.wgs.inspire.chain.rpc.RpcResult;

/**
 * Created by wanggenshen
 * Date: on 2019/11/20 23:42.
 * Description: 接口测试类
 */
public interface RefundService {

    RpcResult<Boolean> refund(String orderId, String reason);
}

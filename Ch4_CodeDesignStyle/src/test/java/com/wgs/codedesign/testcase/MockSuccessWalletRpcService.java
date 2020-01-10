package com.wgs.codedesign.testcase;

import com.wgs.codedesign.testcase.service.WalletRpcService;

/**
 * @author: Created by wanggenshen
 * @date: 2020/1/10 19:43.
 * @description: 模拟转账成功
 */
public class MockSuccessWalletRpcService extends WalletRpcService {

    @Override
    public String moveMoney(String preAssignedId, Long buyerId, Long sellerId, Double amount) {
        return "success";
    }
}

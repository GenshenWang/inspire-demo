package com.wgs.codedesign.模板模式2_回调.回调_demo2;

import com.wgs.codedesign.模板模式2_回调.回调_demo2.third_service.IPayCallback2;

/**
 * @author: wanggenshen
 * @date: 2020/4/10 14:05.
 * @description: 用户自定义支付回调成功的回调处理
 */
public class DidiPayCallbackProcessor implements IPayCallback2{

    @Override
    public void processPaySuccess(long uid, long money, boolean success) {
        System.out.println("滴滴支付 IPayCallback回调处理: 用户" + uid + "微信支付成功金额: " + money);
    }
}

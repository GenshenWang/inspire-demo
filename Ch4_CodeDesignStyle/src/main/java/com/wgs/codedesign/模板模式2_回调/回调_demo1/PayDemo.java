package com.wgs.codedesign.模板模式2_回调.回调_demo1;

/**
 * @author: wanggenshen
 * @date: 2020/4/10 13:56.
 * @description: XXX
 */
public class PayDemo {

    public static void main(String[] args) {
        WechatPayService wechatPayService = new WechatPayService();

        long uid = 1000L;
        long money = 2390;
        wechatPayService.pay(uid, money, new IPayCallback() {
            @Override
            public void processPaySuccess(long uid, long money, boolean success) {
                System.out.println("IPayCallback回调处理: 用户" + uid + "微信支付成功金额: " + money);
            }
        });
    }
}

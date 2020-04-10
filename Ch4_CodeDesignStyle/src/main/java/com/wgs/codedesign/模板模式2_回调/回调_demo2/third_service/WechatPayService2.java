package com.wgs.codedesign.模板模式2_回调.回调_demo2.third_service;

/**
 * @author: wanggenshen
 * @date: 2020/4/10 13:19.
 * @description: 假设WechatPayService2是第三方的服务接口,
 *               IPayCallback2就是扩展点, 提供给用户自定义拓展功能
 */
public class WechatPayService2 {

    public void pay(long uid, long money, IPayCallback2 iPayCallback2) {
        System.out.println("微信支付成功");
        iPayCallback2.processPaySuccess(uid, money, true);
    }
}

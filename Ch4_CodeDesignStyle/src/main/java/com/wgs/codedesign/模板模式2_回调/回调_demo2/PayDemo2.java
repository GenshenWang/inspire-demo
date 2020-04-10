package com.wgs.codedesign.模板模式2_回调.回调_demo2;

import com.wgs.codedesign.模板模式2_回调.回调_demo2.third_service.IPayCallback2;
import com.wgs.codedesign.模板模式2_回调.回调_demo2.third_service.WechatPayService2;

/**
 * @author: wanggenshen
 * @date: 2020/4/10 13:56.
 * @description: XXX
 */
public class PayDemo2 {

    public static void main(String[] args) {
        WechatPayService2 wechatPayService2 = new WechatPayService2();

        long uid = 1000L;
        long money = 2390;
        // 使用DidiPayCallbackProcessor自定义拓展点: IPayCallback2
        wechatPayService2.pay(uid, money, new DidiPayCallbackProcessor());

        // 使用DidiPayCallbackProcessor自定义拓展点: IPayCallback2
        wechatPayService2.pay(uid, money, new CaocaoPayCallbackProcessor());
    }
}

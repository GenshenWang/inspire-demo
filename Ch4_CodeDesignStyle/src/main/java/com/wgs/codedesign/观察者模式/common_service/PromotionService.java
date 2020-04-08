package com.wgs.codedesign.观察者模式.common_service;

/**
 * @author: wanggenshen
 * @date: 2020/4/7 13:08.
 * @description: XXX
 */
public class PromotionService {

    /**
     * 发放新人注册红包
     * @param userId
     */
    public void issueNewUserCoupon(long userId) {
        System.out.println("发放新人礼包" + userId);
    }
}

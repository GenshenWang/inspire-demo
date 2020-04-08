package com.wgs.codedesign.观察者模式.v3_exe_异步阻塞.observer;


import com.wgs.codedesign.观察者模式.common_service.PromotionService;

/**
 * @author: wanggenshen
 * @date: 2020/4/7 13:24.
 * @description: XXX
 */
public class UserCouponObserver implements UserRegisterObserver {

    private PromotionService promotionService;
    public UserCouponObserver() {
        this.promotionService = new PromotionService();
    }

    @Override
    public void handleRegisterEvent(long userId) {
        promotionService.issueNewUserCoupon(userId);
    }
}

package com.wgs.codedesign.观察者模式.v4_exe_EventBus实现.observer;

import com.google.common.eventbus.Subscribe;
import com.wgs.codedesign.观察者模式.common_service.PromotionService;

/**
 * @author: wanggenshen
 * @date: 2020/4/7 13:24.
 * @description: XXX
 */
public class UserCouponObserver {

    private PromotionService promotionService;
    public UserCouponObserver() {
        this.promotionService = new PromotionService();
    }

    @Subscribe
    public void registerCoupon(Long userId) {
        promotionService.issueNewUserCoupon(userId);
    }
}

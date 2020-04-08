package com.wgs.codedesign.观察者模式.v2_exe_同步阻塞;

import com.wgs.codedesign.观察者模式.v2_exe_同步阻塞.service.NotifyService;
import com.wgs.codedesign.观察者模式.v2_exe_同步阻塞.service.PromotionService;
import com.wgs.codedesign.观察者模式.v2_exe_同步阻塞.service.UserService;

/**
 * @author: wanggenshen
 * @date: 2020/4/7 13:07.
 * @description: XXX
 */
public class UserControllerV1 {

    // 依赖注入
    private PromotionService promotionService;
    private UserService userService;
    private NotifyService notifyService;

    public UserControllerV1() {
        this.promotionService = new PromotionService();
        this.userService = new UserService();
        this.notifyService = new NotifyService();
    }

    public void register(String phone, String password) {
        long userId = userService.register(phone, password);

        // 添加逻辑1
        promotionService.issueNewUserCoupon(userId);

        // 添加逻辑2
        notifyService.notify(userId);

        // 添加逻辑3
        // ...

        System.out.println("==注册成功==");
    }

    public static void main(String[] args) {
        UserControllerV1 userControllerV1 = new UserControllerV1();
        userControllerV1.register("1882736239", "rrrreddd");
    }

}

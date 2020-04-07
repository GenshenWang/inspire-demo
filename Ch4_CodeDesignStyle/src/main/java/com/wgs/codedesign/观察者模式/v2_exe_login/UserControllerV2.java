package com.wgs.codedesign.观察者模式.v2_exe_login;

import com.wgs.codedesign.观察者模式.v2_exe_login.observer.NotifyObserver;
import com.wgs.codedesign.观察者模式.v2_exe_login.observer.ObserverManager;
import com.wgs.codedesign.观察者模式.v2_exe_login.observer.UserCouponObserver;
import com.wgs.codedesign.观察者模式.v2_exe_login.service.UserService;

/**
 * @author: wanggenshen
 * @date: 2020/4/7 13:07.
 * @description: XXX
 */
public class UserControllerV2 {

    // 依赖注入
    private UserService userService;
    private ObserverManager observerManager;

    public UserControllerV2() {
        this.observerManager = new ObserverManager();
        this.userService  = new UserService();
    }

    public void register(String phone, String password) {
        long userId = userService.register(phone, password);

        // 添加逻辑1
        // 添加逻辑2
        // 添加逻辑3

        // 以上全部交由observerManager去做, 用观察者模式将用户注册和注册后的操作解耦开来
        observerManager.registerObserver(new UserCouponObserver());
        observerManager.registerObserver(new NotifyObserver());
        observerManager.executeObserver(userId);

        System.out.println("==注册成功==");
    }

    public static void main(String[] args) {
        UserControllerV2 userControllerV2 = new UserControllerV2();
        userControllerV2.register("1882736239", "rrrreddd");
    }

}

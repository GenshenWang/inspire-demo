package com.wgs.codedesign.观察者模式.v4_exe_EventBus实现;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.wgs.codedesign.观察者模式.common_service.UserService;
import com.wgs.codedesign.观察者模式.v4_exe_EventBus实现.observer.NotifyObserver;
import com.wgs.codedesign.观察者模式.v4_exe_EventBus实现.observer.UserCouponObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @author: wanggenshen
 * @date: 2020/4/7 13:07.
 * @description: 异步阻塞的实现方式
 */
public class UserControllerV4 {

    // 依赖注入
    private UserService userService;
    private EventBus eventBus;


    public UserControllerV4() {
        // 同步阻塞实现方式
        // this.eventBus = new EventBus();
        // 异步阻塞实现方式
        this.eventBus = new AsyncEventBus(Executors.newFixedThreadPool(20));
        this.userService  = new UserService();
    }

    public void setRegObservers(List<Object> observers) {
        for (Object observer : observers) {
            eventBus.register(observer);
        }
    }

    public void register(String phone, String password) {
        long userId = userService.register(phone, password);

        // 添加逻辑1
        // 添加逻辑2
        // 添加逻辑3

        // 以上全部交由EventBus
        eventBus.post(userId);

        System.out.println("==注册成功==");
    }

    public static void main(String[] args) {
        UserControllerV4 userControllerV2 = new UserControllerV4();

        List<Object> observers = new ArrayList<>();
        observers.add(new NotifyObserver());
        observers.add(new UserCouponObserver());
        userControllerV2.setRegObservers(observers);

        userControllerV2.register("1882736239", "rrrreddd");
    }

}

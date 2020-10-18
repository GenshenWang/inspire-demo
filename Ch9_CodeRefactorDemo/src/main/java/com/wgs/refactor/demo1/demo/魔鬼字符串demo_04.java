package com.wgs.refactor.demo1.demo;

import com.wgs.refactor.demo1.Food;

public class 魔鬼字符串demo_04 {

    public static final int IOS_VERSION = 7;

    /**
     * 7是啥？
     *
     * alt+command+c / v
     * @param appVersion
     * @param foodId
     * @return
     */
    private Food queryDetail(Integer appVersion, Integer foodId) {
        if (appVersion < IOS_VERSION) {
            return new Food();
        } else {
            // return another info
            return new Food();
        }
    }
}

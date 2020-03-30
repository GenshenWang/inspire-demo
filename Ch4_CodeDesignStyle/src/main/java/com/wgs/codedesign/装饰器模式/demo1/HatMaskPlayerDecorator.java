package com.wgs.codedesign.装饰器模式.demo1;

/**
 * @author: wanggenshen
 * @date: 2020/3/30 13:24.
 * @description: XXX
 */
public class HatMaskPlayerDecorator implements Player{
    private Player player;

    public HatMaskPlayerDecorator(Player player) {
        this.player = player;
    }

    @Override
    public int fight(String userId) {

        int res = player.fight(userId);

        // 在原有类基础上添加功能增强的代码
        res += 300;
        return res;
    }
}

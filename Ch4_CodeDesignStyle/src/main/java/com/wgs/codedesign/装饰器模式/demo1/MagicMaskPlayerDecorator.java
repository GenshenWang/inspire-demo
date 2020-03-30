package com.wgs.codedesign.装饰器模式.demo1;

/**
 * @author: wanggenshen
 * @date: 2020/3/30 12:39.
 * @description: 装饰器类: 带面具的魔法攻击
 */
public class MagicMaskPlayerDecorator implements Player {

    private Player player;

    public MagicMaskPlayerDecorator(Player player) {
        this.player = player;
    }

    @Override
    public int fight(String userId) {

        int res = player.fight(userId);

        // 在原有类基础上添加功能增强的代码
        res += 200;
        return res;
    }
}

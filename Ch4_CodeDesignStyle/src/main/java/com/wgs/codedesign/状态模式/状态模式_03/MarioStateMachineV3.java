package com.wgs.codedesign.状态模式.状态模式_03;

import com.wgs.codedesign.状态模式.状态模式_03.impl.SmallMario;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: wanggenshen
 * @date: 2020/4/17 19:49.
 * @description: 状态机
 */
public class MarioStateMachineV3 {

    @Setter
    @Getter
    private int score;

    @Setter
    @Getter
    private IMario currentState;

    public MarioStateMachineV3() {
        this.score = 0;
        this.currentState = SmallMario.getInstance();
    }

    public void obtainMushroom() {
        this.currentState.obtainMashroomEvent(this);
    }

    public void obtainCape() {
        this.currentState.obtainCapeEvent(this);
    }

    public void obtainFire() {
        this.currentState.obtainFireFlowerEvent(this);
    }

    public void meetMonster() {
        this.currentState.meetMonsterEvent(this);
    }


    @Override
    public String toString() {
        return "MarioStateMachineV3{" +
                "score=" + score +
                ", currentState=" + currentState +
                '}';
    }
}

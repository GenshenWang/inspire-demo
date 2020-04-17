package com.wgs.codedesign.状态模式.状态模式_03.impl;

import com.wgs.codedesign.状态模式.状态模式_03.IMario;
import com.wgs.codedesign.状态模式.状态模式_03.MarioStateMachineV3;
import com.wgs.codedesign.状态模式.状态模式_03.StateV3;

/**
 * @author: wanggenshen
 * @date: 2020/4/17 19:47.
 * @description: XXX
 */
public class FireMario implements IMario {

    private static final FireMario instance = new FireMario();

    private FireMario() {
    }

    public static FireMario getInstance() {
        return instance;
    }

    @Override
    public StateV3 getStateName() {
        return StateV3.FIRE;
    }

    @Override
    public void obtainMashroomEvent(MarioStateMachineV3 stateMachine) {
    }

    @Override
    public void obtainCapeEvent(MarioStateMachineV3 stateMachine) {

    }

    @Override
    public void obtainFireFlowerEvent(MarioStateMachineV3 stateMachine) {

    }

    @Override
    public void meetMonsterEvent(MarioStateMachineV3 stateMachine) {
        stateMachine.setCurrentState(SuperMario.getInstance());
        stateMachine.setScore(stateMachine.getScore() - 300);
    }
}

package com.wgs.codedesign.状态模式.状态模式_03.impl;

import com.wgs.codedesign.状态模式.状态模式_03.IMario;
import com.wgs.codedesign.状态模式.状态模式_03.MarioStateMachineV3;
import com.wgs.codedesign.状态模式.状态模式_03.StateV3;

/**
 * @author: wanggenshen
 * @date: 2020/4/17 19:47.
 * @description: XXX
 */
public class SuperMario implements IMario {

    private static final SuperMario instance = new SuperMario();
    private SuperMario() {
    }

    public static SuperMario getInstance() {
        return instance;
    }

    @Override
    public StateV3 getStateName() {
        return StateV3.SUPER;
    }

    @Override
    public void obtainMashroomEvent(MarioStateMachineV3 stateMachine) {

    }

    @Override
    public void obtainCapeEvent(MarioStateMachineV3 stateMachine) {
        stateMachine.setCurrentState(CapeMario.getInstance());
        stateMachine.setScore(stateMachine.getScore() + 200);
    }

    @Override
    public void obtainFireFlowerEvent(MarioStateMachineV3 stateMachine) {
        stateMachine.setCurrentState(FireMario.getInstance());
        stateMachine.setScore(stateMachine.getScore() + 300);
    }

    @Override
    public void meetMonsterEvent(MarioStateMachineV3 stateMachine) {
        stateMachine.setCurrentState(SmallMario.getInstance());
        stateMachine.setScore(stateMachine.getScore() - 100);
    }
}

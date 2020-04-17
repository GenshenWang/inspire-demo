package com.wgs.codedesign.状态模式.状态模式_03;

/**
 * @author: wanggenshen
 * @date: 2020/4/17 19:44.
 * @description: 状态接口, 定义了所有的事件
 */
public interface IMario {

    StateV3 getStateName();

    void obtainMashroomEvent(MarioStateMachineV3 stateMachine);

    void obtainCapeEvent(MarioStateMachineV3 stateMachine);

    void obtainFireFlowerEvent(MarioStateMachineV3 stateMachine);

    void meetMonsterEvent(MarioStateMachineV3 stateMachine);
}



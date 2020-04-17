package com.wgs.codedesign.状态模式.状态模式_03;

import com.wgs.codedesign.状态模式.查表法_02.MarioStateMachineV2;

/**
 * @author: wanggenshen
 * @date: 2020/4/17 13:48.
 * @description: XXX
 */
public class StateMain3 {

    public static void main(String[] args) {
        System.out.println("======马里奥游戏开始了=======");

        MarioStateMachineV3 stateMachine = new MarioStateMachineV3();
        System.out.println("小马里奥吃了个蘑菇");
        stateMachine.obtainMushroom();
        printScore(stateMachine);

        System.out.println("马里奥得到火焰");
        stateMachine.obtainFire();
        printScore(stateMachine);

        System.out.println("马里奥遇到怪物");
        stateMachine.meetMonster();
        printScore(stateMachine);
    }

    private static void printScore(MarioStateMachineV3 marioStateMachine) {
        System.out.println("score: " + marioStateMachine.getScore() + ", state:" + marioStateMachine.getCurrentState().getStateName());

    }
}

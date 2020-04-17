package com.wgs.codedesign.状态模式.查表法_02;

/**
 * @author: wanggenshen
 * @date: 2020/4/17 13:48.
 * @description: XXX
 */
public class StateMain2 {

    public static void main(String[] args) {
        System.out.println("======马里奥游戏开始了=======");

        MarioStateMachineV2 marioStateMachine = new MarioStateMachineV2();
        System.out.println("小马里奥吃了个蘑菇");
        marioStateMachine.obtainMashroom();
        printScore(marioStateMachine);

        System.out.println("马里奥得到火焰");
        marioStateMachine.obtainFire();
        printScore(marioStateMachine);

        System.out.println("马里奥遇到怪物");
        marioStateMachine.meetMonster();
        printScore(marioStateMachine);
    }

    private static void printScore(MarioStateMachineV2 marioStateMachine) {
        System.out.println("score: " + marioStateMachine.getScore() + ", state:" + marioStateMachine.getCurrentState());

    }
}

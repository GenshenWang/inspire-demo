package com.wgs.codedesign.状态模式.分支法_01;

/**
 * @author: wanggenshen
 * @date: 2020/4/17 13:48.
 * @description: XXX
 */
public class StateMain1 {

    public static void main(String[] args) {
        System.out.println("======马里奥游戏开始了=======");

        MarioStateMachine marioStateMachine = new MarioStateMachine();
        System.out.println("小马里奥吃了个蘑菇");
        marioStateMachine.obtainMashroom();
        printScore(marioStateMachine);

        System.out.println("马里奥得到火焰");
        marioStateMachine.obtainFireFlower();
        printScore(marioStateMachine);

        System.out.println("马里奥遇到怪物");
        marioStateMachine.meetMonster();
        printScore(marioStateMachine);
    }

    private static void printScore(MarioStateMachine marioStateMachine) {
        System.out.println("score: " + marioStateMachine.getScore() + ", state:" + marioStateMachine.getCurrentState());

    }
}

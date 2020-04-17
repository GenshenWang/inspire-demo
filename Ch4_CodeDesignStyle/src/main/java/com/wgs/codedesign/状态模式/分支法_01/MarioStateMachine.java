package com.wgs.codedesign.状态模式.分支法_01;

/**
 * @author: wanggenshen
 * @date: 2020/4/17 12:25.
 * @description: 分支法_01(if...else...)
 */
public class MarioStateMachine {
    private int score;
    private State currentState;

    /**
     * 初始状态为SMALL Mario, 积分为0分
     */
    public MarioStateMachine() {
        this.score = 0;
        this.currentState = State.SMALL;
    }

    public void obtainMashroom() {
        if (State.SMALL.equals(this.currentState)) {
            this.score += 100;
            this.currentState = State.SUPER;
            return;
        }
        throw new RuntimeException("Unsupported state changed");
    }

    public void obtainCape() {
        if (State.SMALL.equals(this.currentState)
                || State.SUPER.equals(this.currentState)) {
            this.score += 200;
            this.currentState = State.CAPE;
            return;
        }
        throw new RuntimeException("Unsupported state changed");
    }

    public void obtainFireFlower() {
        if (State.SMALL.equals(this.currentState)
                || State.SUPER.equals(this.currentState)) {
            this.score += 300;
            this.currentState = State.FIRE;
            return;
        }
        throw new RuntimeException("Unsupported state changed");
    }

    public void meetMonster() {
        if (State.FIRE.equals(this.currentState)) {
            this.score -= 300;
            this.currentState = State.SMALL;
            return;
        }
        if (State.CAPE.equals(this.currentState)) {
            this.score -= 200;
            this.currentState = State.SMALL;
            return;
        }
        if (State.SUPER.equals(this.currentState)) {
            this.score -= 100;
            this.currentState = State.SMALL;
            return;
        }
        throw new RuntimeException("Unsupported state changed");
    }


    public int getScore() {
        return score;
    }

    public State getCurrentState() {
        return currentState;
    }
}

package com.wgs.codedesign.状态模式.查表法_02;


/**
 * @author: wanggenshen
 * @date: 2020/4/17 14:11.
 * @description: 查表法(二维数组)
 */
public class MarioStateMachineV2 {
    private int score;
    private StateV2 currentState;

    // 状态转移 0: 当前状态; 1: 目标状态
    private static final StateV2[][] transitionTable = {
            {StateV2.SUPER, StateV2.CAPE, StateV2.FIRE, StateV2.SMALL},
            {StateV2.SUPER, StateV2.CAPE, StateV2.FIRE, StateV2.SMALL},
            {StateV2.CAPE, StateV2.CAPE, StateV2.CAPE, StateV2.SMALL},
            {StateV2.FIRE, StateV2.FIRE, StateV2.FIRE, StateV2.SMALL}
    };

    // 积分变化: 0:当前积分; 1: 变化积分
    private static final int[][] actionTable = {
            {+100, +200, +300, 0},
            {0, +200, +300, -100},
            {0, 0, 0, -200},
            {0, 0, 0, -300}
    };

    /**
     * 初始状态
     */
    public MarioStateMachineV2() {
        this.score = 0;
        this.currentState = StateV2.SMALL;
    }

    public void obtainMashroom() {
        fireEvent(Event.OBTAIN_MUSHROOM);
    }

    public void obtainFire() {
        fireEvent(Event.OBTAIN_FIRE);
    }

    public void obtainCape() {
        fireEvent(Event.OBTAIN_CAPE);
    }

    public void meetMonster() {
        fireEvent(Event.MEET_MONSTER);
    }

    public void fireEvent(Event event) {
        int currentStateValue = this.currentState.getValue();
        int targetStateValue = event.getValue();

        this.currentState = transitionTable[currentStateValue][targetStateValue];
        this.score += actionTable[currentStateValue][targetStateValue];
    }

    public int getScore() {
        return score;
    }

    public StateV2 getCurrentState() {
        return currentState;
    }


    public static void main(String[] args) {
        System.out.println(actionTable[0][1]);
    }



}

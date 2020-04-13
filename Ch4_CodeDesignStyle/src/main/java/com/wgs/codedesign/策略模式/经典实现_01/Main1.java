package com.wgs.codedesign.策略模式.经典实现_01;

/**
 * @author: wanggenshen
 * @date: 2020/4/13 21:38.
 * @description: XXX
 */
public class Main1 {

    public static void main(String[] args) {
        String method = "A";
        if ("A".equals(method)) {
            Strategy strategy = new ConcretStrategyA();
            strategy.method();
        } else if ("B".equals(method)) {
            Strategy strategy = new ConcretStrategyA();
            strategy.method();
        }
        
    }
}

package com.wgs.codedesign.解释器模式;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: wanggenshen
 * @date: 2020/5/3 18:32.
 * @description: XXX
 */
public class Demo1 {

    private static int calculate(String expression) {

        String[] strArr = expression.trim().split(" ");

        Deque<Integer> numList = new LinkedList<>();
        for (int i = 0; i < strArr.length / 2 + 1; i++) {
            numList.add(Integer.parseInt(strArr[i]));
        }


        List<String> calSymbolList = new LinkedList<>();
        for (int i = strArr.length / 2 + 1; i < strArr.length; i++) {
            calSymbolList.add(strArr[i]);
        }

        int res = 0;
        for (int i = 0; i < calSymbolList.size(); i++) {
            int num1 = numList.pollFirst();
            int num2 = numList.pollFirst();
            String symbol = calSymbolList.get(i);
            
            if ("+".equals(symbol)) {
                res = num1 + num2;
            } else if ("-".equals(symbol)) {
                res = num1 - num2;
            } else if ("*".equals(symbol)) {
                res = num1 * num2;
            } else if ("/".equals(symbol)) {
                res = num1 / num2;
            }

            numList.addFirst(res);

        }

        return res;
    }

    public static void main(String[] args) {
        int res = calculate("8 3 2 4 - + *");
        System.out.println(res);
    }

}

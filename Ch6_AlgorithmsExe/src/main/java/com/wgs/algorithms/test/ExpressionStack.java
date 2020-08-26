package com.wgs.algorithms.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExpressionStack {


    private Stack<Integer> numStack = new Stack<>();
    private Stack<Character> expressStack = new Stack<>();

    private static Map<Character, Integer> map = new HashMap<>();
    static  {
        map.put('+', 0);
        map.put('-', 0);
        map.put('*', 1);
        map.put('/', 1);
    }

    public int calculate(String str) {

        char[] strArrs = str.toCharArray();
        for (int i = 0; i < strArrs.length; i++) {
            char c = strArrs[i];
            // 数字直接放入
            if (isNum(c)) {
                numStack.push(char2Int(c));
            } else {

                if (expressStack.isEmpty()) {
                    expressStack.push(c);
                    continue;
                }
                char peekCharacter =  expressStack.peek();
                if (compareCharPriority(c, peekCharacter) > 0) {
                    expressStack.push(c);
                } else if (compareCharPriority(c, peekCharacter) <= 0){
                    while (!expressStack.isEmpty()) {
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        char expression = expressStack.pop();
                        int res = cal(expression, num2, num1);
                        numStack.push(res);
                    }
                    expressStack.push(c);

                }
            }
        }

        while (!numStack.isEmpty() && !expressStack.isEmpty()) {
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            char expression = expressStack.pop();
            int res = cal(expression, num2, num1);
            numStack.push(res);
        }

        return numStack.pop();
    }

    private int cal(char expression, int num1, int num2) {
        if (expression == '+') {
            return num1 + num2;
        } else if (expression == '-') {
            return num1 - num2;
        } else if (expression == '*') {
            return num1 * num2;
        } else if (expression== '/') {
            return num1 / num2;
        }
        throw new RuntimeException("Unknown expression!");
    }

    private int compareCharPriority(char a, char b) {
        return map.get(a) - map.get(b);
    }

    private boolean isNum(char c) {
        return !map.containsKey(c);
    }

    private int char2Int(char c) {
        return Integer.parseInt(String.valueOf(c));
    }

    public static void main(String[] args) {
        System.out.println(new ExpressionStack().calculate("1+1"));
        char c = '2';
        char a = '3';
        System.out.println(Integer.parseInt(String.valueOf(c)) + Integer.parseInt(String.valueOf(a)));
    }
}

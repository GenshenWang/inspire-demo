package com.wgs.algorithms.栈;

import java.util.Stack;

/**
 * @author: wanggenshen
 * @date: 2020/5/18 00:40.
 * @description: 使用栈来模拟计算 34+13*9+44-12/3 过程
 */
public class ExpressionWithStack {

    private static final String ADD_FLAG = "+";
    private static final String DEDUCT_FLAG = "-";
    private static final String MULTIPLY_FLAG = "*";
    private static final String DIVIDE_FLAG = "/";

    private Stack<Integer> stack1 = new Stack<>();
    private Stack<String> stack2 = new Stack<>();

    public int calculate(String[] expression) {
        for (String s : expression) {
            if (stack2.size() > 0) {
                while (stack2.size() > 0 && isExpression(s) && comparePriorityLevel(stack2.peek(), s) <= 0) {
                    int num2 = stack1.pop();
                    int num1 = stack1.pop();
                    String exp = stack2.pop();
                    stack1.push(cal(num1, num2, exp));

                }
            }

            if (isExpression(s)) {
                stack2.push(s);
            } else {
                stack1.push(Integer.valueOf(s));
            }
        }

        while (stack1.size() > 0 && stack2.size() > 0) {
            int num2 = stack1.pop();
            int num1 = stack1.pop();
            String exp = stack2.pop();
            stack1.push(cal(num1, num2, exp));
        }
        return stack1.peek();
    }

    private int cal(int num1, int num2, String exp) {
        if ("+".equals(exp)) {
            return num1 + num2;
        } else if ("-".equals(exp)) {
            return num1 - num2;
        } else if ("*".equals(exp)) {
            return num1 * num2;
        } else {
            return num1 / num2;
        }

    }

    private boolean isExpression(String c) {
        return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/");
    }


    private int comparePriorityLevel(String baseFlag, String flag2) {
        if (baseFlag.equals(flag2)) {
            return 0;
        }
        if (ADD_FLAG.equals(baseFlag) || DEDUCT_FLAG.equals(baseFlag)) {
            if (ADD_FLAG.equals(flag2) || DEDUCT_FLAG.equals(flag2)) {
                return 0;
            }
            return MULTIPLY_FLAG.equals(flag2) || DIVIDE_FLAG.equals(flag2) ? 1 : -1;
        }

        if (MULTIPLY_FLAG.equals(baseFlag) || DIVIDE_FLAG.equals(baseFlag)) {
            if (MULTIPLY_FLAG.equals(flag2) || DIVIDE_FLAG.equals(flag2)) {
                return 0;
            }
            return MULTIPLY_FLAG.equals(flag2) || DIVIDE_FLAG.equals(flag2) ? 1 : -1;
        }

        return 0;
    }

    public static void main(String[] args) {
        ExpressionWithStack main = new ExpressionWithStack();

        String[] str = new String[11];
        str[0] = "34";
        str[1] = "+";
        str[2] = "13";
        str[3] = "*";
        str[4] = "9";
        str[5] = "+";
        str[6] = "44";
        str[7] = "-";
        str[8] = "12";
        str[9] = "/";
        str[10] = "3";
        int res = main.calculate(str);
        System.out.println(res);

        // 10 + 12*2 - 3*5 + 15 / 3 = 24
        String[] str2 = new String[13];
        str2[0] = "10";
        str2[1] = "+";
        str2[2] = "12";
        str2[3] = "*";
        str2[4] = "2";
        str2[5] = "-";
        str2[6] = "3";
        str2[7] = "*";
        str2[8] = "5";
        str2[9] = "+";
        str2[10] = "15";
        str2[11] = "/";
        str2[12] = "3";
        int res2 = main.calculate(str2);
        System.out.println(res2);


    }
}

package com.wgs.codedesign.解释器模式.v2_解释器;

import com.wgs.codedesign.解释器模式.v2_解释器.expression.*;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: wanggenshen
 * @date: 2020/5/4 00:33.
 * @description: XXX
 */
public class ExpressionInterpreter {
    private Deque<Expression> numbers = new LinkedList<>();

    public int interpret(String expression) {
        String[] elements = expression.split(" ");
        for (int i = 0; i < elements.length / 2 + 1; i++) {
            numbers.addLast(new NumberExpression(elements[i]));
        }

        for (int i = elements.length / 2 + 1; i < elements.length; i++) {
            String symbol = elements[i];
            boolean isValid = "+".equals(symbol) || "-".equals(symbol) || "*".equals(symbol)
                    || "/".equals(symbol);
            if (!isValid) {
                throw new RuntimeException("Invalid expression symbol");
            }

            Expression exp1 = numbers.pollFirst();
            Expression exp2 = numbers.pollFirst();

            Expression res = null;
            if (symbol.equals("+")) {
                res = new AdditionExpression(exp1, exp2);
            } else if (symbol.equals("-")) {
                res = new SubstractionExpression(exp1, exp2);
            } else if (symbol.equals("*")) {
                res = new MultiplicationExpression(exp1, exp2);
            } else if (symbol.equals("/")) {
                res = new DivisionExpression(exp1, exp2);
            }

            int resInt = res.interpret();
            numbers.addFirst(new NumberExpression(resInt));
        }

        return numbers.pop().interpret();
    }

    public static void main(String[] args) {
        int res = new ExpressionInterpreter().interpret("8 3 2 4 - + *");
        System.out.println(res);
    }
}

package com.wgs.algorithms.队列.exe;

import java.util.Stack;

/**
 * @author: wanggenshen
 * @date: 2020/5/24 19:00.
 * @description: 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 */
public class QueueWithTwoStack {

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        if(stack1.isEmpty() && stack2.isEmpty()) {
            stack1.push(node);
            return;
        } else {
            while(!stack2.isEmpty()) {
                int stack2Node = stack2.pop();
                stack1.push(stack2Node);
            }
            stack1.push(node);
        }
    }

    public int pop() {
        while(!stack1.isEmpty()) {
            int node = stack1.pop();
            stack2.push(node);
        }
        return stack2.pop();
    }
}

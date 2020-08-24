package com.wgs.algorithms.leetcode;

import java.util.Stack;

public class MinStack_155 {

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    /** initialize your data structure here. */
    public MinStack_155() {
        stack1 = new Stack();
        stack2 = new Stack();
    }

    public void push(int x) {
        stack1.push(x);

        if (stack2.isEmpty() || x < stack2.peek()) {
            stack2.push(x);
        } else {
            stack2.push(stack2.peek());
        }
    }

    public void pop() {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            return;
        }
        stack1.pop();
        stack2.pop();
    }

    public int top() {
        if (stack1 == null || stack1.isEmpty()) {
            return -1;
        }
        return stack1.peek();
    }

    public int getMin() {
        if (stack1 == null || stack1.isEmpty()) {
            return -1;
        }
        return stack2.peek();
    }

}

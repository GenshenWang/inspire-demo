package com.wgs.algorithms.栈;

import java.util.Stack;

/**
 * @author: wanggenshen
 * @date: 2020/5/17 23:33.
 * @description:
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数
   （时间复杂度应为O（1））。
    注意：保证测试中不会当栈为空的时候，对栈调用pop()或者min()或者top()方法。
 */
public class MinStack {
    private Stack<Integer> stack = new Stack();
    private Stack<Integer> minStack = new Stack();

    public void push(int node) {
        stack.push(node);
        if (minStack == null || minStack.size() <= 0) {
            minStack.push(node);
        } else {
            int stackMinNode = min();
            if (node < stackMinNode) {
                minStack.push(node);
            } else {
                minStack.push(stackMinNode);
            }
        }
    }

    public void pop() {
        if (stack != null && stack.size() > 0) {
            stack.pop();
        }
        if (minStack != null && minStack.size() > 0) {
            minStack.pop();
        }
    }

    public int top() {
        if (minStack != null && minStack.size() > 0) {
            return minStack.peek();
        }
        return -1;
    }

    public int min() {
        if (minStack != null && minStack.size() > 0) {
            return minStack.peek();
        }
        return -1;
    }
}

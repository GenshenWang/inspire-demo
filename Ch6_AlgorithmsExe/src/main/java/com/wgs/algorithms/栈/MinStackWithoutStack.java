package com.wgs.algorithms.栈;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/5/18 00:05.
 * @description: 不使用栈, 使用集合实现
 */
public class MinStackWithoutStack {
    private List<Integer> stack = new ArrayList();
    private List<Integer> minStack = new ArrayList();

    public void push(int node) {
        stack.add(node);
        if (minStack == null || minStack.size() <= 0) {
            minStack.add(node);
        } else {
            int stackMinNode = min();
            if (node < stackMinNode) {
                minStack.add(node);
            } else {
                minStack.add(stackMinNode);
            }
        }
    }

    public void pop() {
        if (stack != null && stack.size() > 0) {
            stack.remove(stack.size() - 1);
        }
        if (minStack != null && minStack.size() > 0) {
            minStack.remove(minStack.size() - 1);
        }
    }

    public int top() {
        if (minStack != null && minStack.size() > 0) {
            return minStack.get(minStack.size() - 1);
        }
        return -1;
    }

    public int min() {
        return top();
    }

    public static void main(String[] args) {
        MinStackWithoutStack main = new MinStackWithoutStack();
        main.push(3);
        System.out.println(main.min());

        main.push(2);
        System.out.println(main.min());

        main.push(4);
        System.out.println(main.min());

        main.pop();
        System.out.println(main.min());

        main.pop();
        System.out.println(main.min());


        main.push(1);
        System.out.println(main.min());
    }
}

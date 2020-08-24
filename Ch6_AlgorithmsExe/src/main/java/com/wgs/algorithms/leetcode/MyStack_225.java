package com.wgs.algorithms.leetcode;

public class MyStack_225 {

    private MyQueue mainQueue;
    private MyQueue helpQueue;

    /** Initialize your data structure here. */
    public MyStack_225() {
        mainQueue = new MyQueue(10);
        helpQueue = new MyQueue(10);
    }

    /** Push element x onto stack. */
    public void push(int x) {
        while (!mainQueue.isEmpty()) {
            helpQueue.enqueue(mainQueue.dequeue());
        }

        mainQueue.enqueue(x);

        while (!helpQueue.isEmpty()) {
            mainQueue.enqueue(helpQueue.dequeue());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return mainQueue.dequeue();
    }

    /** Get the top element. */
    public int top() {
        int top = pop();
        push(top);
        return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return mainQueue.isEmpty();
    }

    static class MyQueue {
        private int count;
        private int size;
        private int[] queue;

        public MyQueue(int n) {
            this.count = 0;
            this.size = n;
            queue = new int[n];
        }

        public void enqueue(int item) {
            if (count  + 1 > size) {
                return;
            }
            queue[count++] = item;
        }

        public int dequeue() {
            if (count - 1 < 0) {
                return -1;
            }
            int dequeue = queue[0];
            queue[0] = 0;
            System.arraycopy(queue, 1, queue, 0 ,size - count);
            count--;
            return dequeue;
        }

        public boolean isEmpty() {
            return count <= 0;
        }
    }

    public static void main(String[] args) {
        MyStack_225 myStack = new MyStack_225();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top());
        System.out.println(myStack.pop());
        System.out.println( myStack.empty());
    }
}

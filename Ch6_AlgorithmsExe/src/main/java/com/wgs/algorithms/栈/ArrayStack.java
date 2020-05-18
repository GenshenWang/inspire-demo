package com.wgs.algorithms.栈;

/**
 * @author: wanggenshen
 * @date: 2020/5/18 09:29.
 * @description: 顺序栈实现: 使用数组实现栈
 */
public class ArrayStack<E> {

    private Object[] arrs;
    // real node size
    private int count;
    // stack size
    private int size;

    public ArrayStack(int size) {
        arrs = new Object[size];
        this.size = size;
        this.count = 0;
    }

    /**
     * 入栈操作
     *
     * @param num
     */
    public void push(E num) {
        if (count >= size) {
            // 可以进行动态扩容
            throw new RuntimeException("Exceed stack initial size");
        }
        arrs[count] = num;
        count++;
    }

    /**
     * 出栈操作
     * 
     * @return
     */
    public E pop() {
        if (count <= 0) {
            throw new RuntimeException("Stack is empty");
        }
        E obj = (E) arrs[count - 1];

        Object[] newArrs = new Object[this.size];
        System.arraycopy(arrs, 0, newArrs, 0, count - 1);
        arrs = newArrs;
        count--;
        return obj;
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>(10);
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.pop());
        System.out.println(stack.pop());

        stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }
}

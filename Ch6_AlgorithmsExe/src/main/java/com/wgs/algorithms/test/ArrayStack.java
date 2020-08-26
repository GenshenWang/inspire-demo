package com.wgs.algorithms.test;

public class ArrayStack {

    // 栈大小
    private int size;
    // 数组模拟栈
    private int[] nums;
    // 栈中元素个数
    private int count;

    public ArrayStack(int size) {
        nums = new int[size];
        this.size = size;
        this.count = 0;
    }

    public boolean push(int item) {
        if (count >= size) {
            return false;
        }
        nums[count] = item;
        count++;
        return true;
    }

    public int pop() {
        if (count == 0) {
            return -1;
        }
        int pop = nums[count - 1];
        count--;
        return pop;
    }
}

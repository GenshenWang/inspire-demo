package com.wgs.algorithms.offer;

import java.util.ArrayList;

/**
 * 剑指offer - JZ42
 *
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
 * 如果有多对数字的和等于S，输出两个数的乘积最小的。
 */
public class FindNumbersWithSum_JZ42 {


    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        ArrayList<Integer> result = new ArrayList<>();
        int start = 0;
        int end =array.length - 1;
        while (end >= start){
            int num1 = array[start];
            int num2 = array[end];
            if (num1 + num2 == sum) {
                result.add(num1);
                result.add(num2);
                return result;
            } else if(num1 + num2 > sum) {
                end--;
            } else {
                start++;
            }
        }
        return result;
    }
}

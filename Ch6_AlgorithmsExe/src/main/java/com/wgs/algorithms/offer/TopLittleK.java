package com.wgs.algorithms.offer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 剑指offer - 最小的K个数
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 */
public class TopLittleK {

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        if (input == null || input.length <= 0 || k < 0 || k > input.length) {
            return null;
        }

        // 初始化
        int[] kNums = new int[k];
        for (int i = 0; i < k; i++) {
            kNums[i] = input[i];
        }

        // 构建大顶堆
        for (int i = input.length / 2 - 1; i >= 0 ; i--) {
            buildMaxHeap(kNums, i, kNums.length);
        }

        // 比堆顶小的值，都放入堆中
        for (int i = k; i < input.length; i++) {
            int temp = kNums[0];
           if (input[i] < temp) {
               kNums[0] = input[i];

               // 重新排序
               buildMaxHeap(kNums, 0, kNums.length);
           }

        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < kNums.length; i++) {
            result.add(kNums[i]);
        }

        return result;
    }

    private void buildMaxHeap(int[] nums, int index, int len) {
        int left = index * 2 + 1;
        int right = left + 1;
        int min = index;
        if (left < len && nums[left] > nums[min]) {
            min = left;
        }

        if (right < len && nums[right] > nums[min]) {
            min = right;
        }

        if (index != min) {
            int temp = nums[index];
            nums[index] = nums[min];
            nums[min] = temp;

            // 遍历子节点
            buildMaxHeap(nums, min, len);
        }
    }

    private void swap(int[] arrs, int i, int j) {
        int temp = arrs[i];
        arrs[i] = arrs[j];
        arrs[j] = temp;

    }

    public static void main(String[] args) {
        int[] nums =new int[1];
        new TopLittleK().GetLeastNumbers_Solution(nums, 4);
    }
}

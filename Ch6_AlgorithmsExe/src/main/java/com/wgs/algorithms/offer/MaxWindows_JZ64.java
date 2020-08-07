package com.wgs.algorithms.offer;

import java.util.ArrayList;

/**
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，
 * 他们的最大值分别为{4,4,6,6,6,5}；
 * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
 * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 * 窗口大于数组长度的时候，返回空
 */
public class MaxWindows_JZ64 {

    /**
     * 时间复杂度：O(n*k)
     * 空间复杂度：O(1)
     * @param num
     * @param size
     * @return
     */
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        if(size <= 0 || size > num.length) {
            return new ArrayList<>();
        }

        ArrayList<Integer> result = new ArrayList<Integer>();
        int start = 0;
        int end = size - 1;
        while (end < num.length) {
            int maxNum = findMaxNum(num, start, end);
            result.add(maxNum);
            start++;
            end++;
        }
        return result;
    }

    private int findMaxNum(int[] num, int start, int end) {
        int max = num[start];
        for (int i = start + 1; i <= end; i++) {
            if (num[i] > max) {
                max = num[i];
            }
        }
        return max;
    }

    /**
     * 使用最小堆，每次只需要跟堆顶数据比较，减少重复比较的次数
     *
     * 时间复杂度：O(n*logk)
     * 空间复杂度：o(k)
     * @param num
     * @param size
     * @return
     */
    public ArrayList<Integer> maxInWindows2(int [] num, int size) {
        if(size <= 0 || size > num.length) {
            return new ArrayList<>();
        }

        ArrayList<Integer> result = new ArrayList<Integer>();

        // 初始化
        int[] kNums = new int[size];
        for (int i = 0; i < size; i++) {
            kNums[i] = num[i];
        }

        // 构建大顶堆
        for (int i = kNums.length / 2 - 1; i >= 0; i--) {
            buildMaxHeap(kNums, i, kNums.length);
        }

        result.add(kNums[0]);

        int count = 0;
        for (int i = size + 1; i < num.length; i++) {
            count++;
            if (count == 3) {
                result.add(kNums[0]);
                count = 0;
                continue;
            }
            if (num[i] >  kNums[0]) {
                kNums[0] = num[i];
            }
        }

        return result;
    }

    private void buildMaxHeap(int[] nums, int index, int len) {
        int left = 2 * index + 1;
        int right = left + 1;
        int max = index;
        if (left < len && nums[left] > nums[index]) {
            max = left;
        }
        if (right < len && nums[right] > nums[index]) {
            max = right;
        }
        if (index != max) {
            int temp = nums[index];
            nums[index] = nums[max];
            nums[max] = temp;

            buildMaxHeap(nums, max, len);
        }
        
    }



    public static void main(String[] args) {
        int[] nums = {2,3,4,2,6,2,5,1};
        new MaxWindows_JZ64().maxInWindows2(nums, 3);
    }
}

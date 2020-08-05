package com.wgs.algorithms.offer;

/**
 * 剑指offer - JZ37
 * 统计一个数字在排序数组中出现的次数。如;[1,2,3,3,3,3,4,5],3 , 输出 4
 *
 * 考点： 二分查找法
 *
 */
public class GetNumberOfK_JZ37 {

    public int GetNumberOfK(int [] array , int k) {
        int start = 0;
        int end = array.length - 1;

        int index = findKIndexBy2Search(array, start, end, k);
        int count = 0;
        for (int i = index; i >= 0; i--) {
            if (array[i] == k) {
                count++;
            }
        }

        for (int i = index + 1; i < array.length; i++) {
            if (array[i] == k) {
                count++;
            }
        }

        return count;
    }

    // 非递归写法
    private int findKIndexBy2Search2(int [] array, int start, int end, int target) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (array[mid] > target) {
                start = mid + 1;
            } else if (array[mid] < target) {
                end = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;

    }

    // 递归写法
    private int findKIndexBy2Search(int [] array, int start, int end, int target) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;
        if (array[mid] > target) {
            return findKIndexBy2Search(array, mid + 1, end, target);
        } else if (array[mid] < target) {
            return findKIndexBy2Search(array, start, mid - 1, target);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,3,3,3,4,5};
        new GetNumberOfK_JZ37().GetNumberOfK(nums, 3);
    }
}


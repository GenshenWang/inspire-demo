package com.wgs.algorithms.leetcode;

public class NextGreaterElement_496 {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];

        int  count = 0;
        for (int i = 0; i < nums1.length; i++) {
            int num1 = nums1[i];
            int index = findNumInNums2Index(num1, nums2);
            // 未找到
            if (index == -1) {
                res[count++] = -1;
                continue;
            }

            // nums2中找到比num1大数字
            int bigNum = Integer.MIN_VALUE;
            for (int j = index + 1; j < nums2.length; j++) {
                if (nums2[j] > num1) {
                    bigNum = nums2[j];
                    break;
                }
            }
            if (bigNum > Integer.MIN_VALUE) {
                res[count++] = bigNum;
            } else {
                res[count++] = -1;
            }
        }

        return res;

    }

    private int findNumInNums2Index(int target, int[] nums2) {
        for (int i = 0; i < nums2.length; i++) {
            if (nums2[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        new NextGreaterElement_496().nextGreaterElement(nums1, nums2);
    }
}

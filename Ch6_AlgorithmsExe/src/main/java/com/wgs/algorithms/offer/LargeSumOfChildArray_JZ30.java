package com.wgs.algorithms.offer;

/**
 * 连续子数组的最大和
 *
 * :{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
 * 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
 *
 */
public class LargeSumOfChildArray_JZ30 {

    public int FindGreatestSumOfSubArray(int[] array) {

        int len = array.length;
        int end = 0;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        while (end < len) {
            sum += array[end];
            if (sum > max) {
                max = sum;
            }
            if (sum <= 0) {
                sum = 0;
            }
            end++;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arrs = {-2,-8,-1,-5,-9};
        //int[] arrs = {6,-3,-2,7,-15,1,2,2};
        new LargeSumOfChildArray_JZ30().FindGreatestSumOfSubArray(arrs);
    }
}

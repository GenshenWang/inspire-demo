package com.wgs.algorithms.offer;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 *
 * 1,2,3,4,5,6,7 -》 1,3,5,7,2,4,6
 */
public class ExchangeNums_JZ13 {

    /**
     * 时间复杂度： O(n)
     * 空间复杂度：O(1)
     * 思路：从前往后，如果是
     *       偶数+奇数：（1）两数相邻，直接交换；（2）不相邻，就将奇数移动到偶数前的位置；
     *       偶数 + 偶数： 右指针移动，直至遇到奇数为之
     *       奇数 + 奇数/奇数 + 偶数： 左右指针分别右移
     * @param nums
     */
    public void reOrderArray(int[] nums) {
        int start = 0;
        int end = start + 1;
        while (end < nums.length) {
            int leftNum = nums[start];
            int rightNum = nums[end];
            // 左偶右奇
            if (isEven(leftNum) && isOdd(rightNum)) {
                if (end - start == 1) {
                    int temp = nums[end];
                    nums[end] = nums[start];
                    nums[start] = temp;


                }
                if (end - start > 1) {
                    int temp = nums[end];
                    for (int i = end; i > start; i--) {
                        nums[i] = nums[i - 1];
                    }
                    nums[start] = temp;
                }

                start++;
                end++;
            } else if (isEven(leftNum) && isEven(rightNum)) {
                // 左偶右偶
                end++;
            } else {
                start++;
                end++;
            }
        }

        System.out.println(nums);
    }


    /**
     * 不用双指针， 使用辅助数组
     * 时间复杂度： O(n)
     * 空间复杂度： O(n)
     * @param nums
     */
    public void reOrderArray2(int[] nums) {
        int[] tempNums =new int[nums.length];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (isOdd(nums[i])) {
                tempNums[index++] = nums[i];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (isEven(nums[i])) {
                tempNums[index++] = nums[i];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = tempNums[i];
        }
    }

    private boolean isEven(int num) {
        return num % 2 == 0;
    }

    private boolean isOdd(int num) {
        return num % 2 != 0;
    }


    public static void main(String[] args) {
        int[] nums = {2,4,6,1,3,5,7};
        new ExchangeNums_JZ13().reOrderArray(nums);
    }
}

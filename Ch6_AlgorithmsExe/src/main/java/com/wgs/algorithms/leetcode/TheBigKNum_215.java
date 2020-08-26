package com.wgs.algorithms.leetcode;

public class TheBigKNum_215 {
    public int findKthLargest(int[] nums, int k) {

        //return kthLargestByHeap(nums, k);
        return kthLargestByQuickSort(nums, k);
    }


    /******************************************************************************************************************
     *
     * 快排排序
     *
     * ****************************************************************************************************************/
    private int kthLargestByQuickSort(int[] nums, int k) {
        int res = quickSort(nums, k, 0 , nums.length - 1);
        return res;
    }

    private int quickSort(int[] nums, int k, int start, int end) {
        if (start <= end) {
            int pivotKey = partition(nums, start, end);
            if (pivotKey == nums.length - k) {
                return nums[pivotKey];
            } else if (pivotKey < nums.length - k) {
                return quickSort(nums, k, pivotKey + 1, end);
            } else {
                return quickSort(nums, k, start, pivotKey);
            }
        }

        return -1;
    }

    /**
     * 从小到大
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int partition(int[] nums, int start, int end) {
        int target = nums[start];
        while (start < end) {
            while (start < end && nums[end] >= target) {
                end--;
            }
            swap(nums, start, end);

            while (start < end && nums[start] <= target) {
                start++;
            }
            swap(nums, start, end);
        }

        return start;

    }


    /******************************************************************************************************************
     *
     * 堆排序
     *
     * ****************************************************************************************************************/
    private int kthLargestByHeap(int[] nums, int k) {
        if (k <= 0 || k > nums.length) {
            return -1;
        }

        // 初始化
        int[] kNums = new int[k];
        for (int i = 0; i < k; i++) {
            kNums[i] = nums[i];
        }

        // 构建小顶堆
        for (int i = k / 2 - 1; i >= 0; i--) {
            heapSort(kNums, i, k);
        }

        //与小顶堆上最小的树比较，大于则加入重新构建。最终返回最大的k个数
        for (int i = k; i < nums.length; i++) {
            // 小顶堆最小的一个数
            int topMinNum = kNums[0];
            if (nums[i] > topMinNum) {
                kNums[0] = nums[i];

                heapSort(kNums, 0, k);
            }
        }

        return kNums[0];
    }

    /**
     * 最小堆
     * @param arrs
     * @param index
     * @param len
     */
    private void heapSort(int[] arrs, int index, int len) {
        int left = index * 2 + 1;
        int right = left + 1;
        int min = index;
        if (left < len && arrs[left] < arrs[min]) {
            min = left;
        }
        if (right < len && arrs[right] < arrs[min]) {
            min = right;
        }

        if (min != index) {
            swap(arrs, min, index);
            heapSort(arrs, min, len);
        }

    }

    private void swap(int[] arrs, int index1, int index2) {
        int temp = arrs[index1];
        arrs[index1] = arrs[index2];
        arrs[index2] = temp;
    }

    public static void main(String[] args) {
        //int[] nums = {7,6,5,4,3,2,1};
        int[] nums = {3,2,1,5,6,4};
        new TheBigKNum_215().findKthLargest(nums, 2);
    }
}

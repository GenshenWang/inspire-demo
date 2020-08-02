package com.wgs.algorithms.排序;

/**
 * 堆排序
 *  时间复杂度：O(nlogn)
 *  空间复杂度： O(1)
 *  算法不稳定
 */
public class HeapSort {

    // 最小堆
    public void littleHeap(int[] nums) {

        // 构建最小堆，返回的是1 2 4 3 7 9  8 6 5
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            buildHeap(nums, i, nums.length);
        }

        // 构建完的堆只能保证最上面的节点是最小值，但是不能保证剩下的堆根节点是最小的
        // 所以应该再比较, 最后返回 9 8 7 6 5 4 3 2 1
        for (int i = nums.length - 1; i > 0; i--) {
            // 将堆最上面的最小值放到最后
            swap(nums, 0, i);
            buildHeap(nums, 0, i - 1);
        }
    }

    private void buildHeap(int[] nums, int index, int length) {
        // 左节点位置
        int left = index * 2 + 1;
        // 右节点位置
        int right = left + 1;

        // 左右节点与根节点比较大小， largest为左右节点值最小的索引
        int largest = index;
        if (left < length && nums[left] < nums[largest]) {
            largest = left;
        }
        if (right < length && nums[right] < nums[largest]) {
            largest = right;
        }

        if (index != largest) {
            swap(nums, index, largest);

            buildHeap(nums, largest, length);
        }

    }

    public static void main(String[] args) {
        int[] nums = {5, 1, 9,3,7,4,8,6,2};


        new HeapSort().littleHeap(nums);

        System.out.println(1);

    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

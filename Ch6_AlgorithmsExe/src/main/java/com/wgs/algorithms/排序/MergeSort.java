package com.wgs.algorithms.排序;

public class MergeSort {

    public static void main(String[] args) {
        int[] arrs = {2,34,56,2,11,4,6,0};
        int[] tmp = new int[arrs.length];
        new MergeSort().mergeSort(arrs, 0, arrs.length - 1, tmp);
        System.out.println(arrs);
    }

    public void mergeSort(int[] arrs, int start, int end, int[] tmp) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            // 左边
            mergeSort(arrs, start, mid, tmp);
            // 右边
            mergeSort(arrs, mid + 1, end, tmp);
            // 合并排序
            merge(arrs, start, mid, end, tmp);
        }
    }

    private void merge(int[] arrs, int start, int mid, int end, int[] tmp) {
        int index = 0;
        int left = start;
        int right = mid + 1;
        while (left <= mid && right <= end) {
            if (arrs[left] < arrs[right]) {
                tmp[index++] = arrs[left++];
            } else {
                tmp[index++] = arrs[right++];
            }
        }

        // 左边有剩余
        while (left <= mid) {
            tmp[index++] = arrs[left++];
        }

        while (right <= end) {
            tmp[index++] = arrs[right++];
        }

        //copy
        for (int i = 0; i < index; i++) {
            arrs[start + i] = tmp[i];
        }

    }
}

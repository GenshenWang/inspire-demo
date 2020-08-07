package com.wgs.algorithms.offer;

/**
 * JZ51 - 构建乘积数组
 *
 * 给定一个数组A[0,1,...,n-1], 请构建一个数组B[0,1,...,n-1],
 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
 * 不能使用除法。（注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）
 * 对于A长度为1的情况，B无意义，故而无法构建，因此该情况不会存在。
 */
public class MultiplyArray_JZ51 {

    public int[] multiply(int[] A) {
        int[] bNums = new int[A.length];
        for (int i = 0; i < bNums.length; i++) {
            bNums[i] = buildBNum(i, A);
        }
        return bNums;
    }

    private int buildBNum(int i, int[] A) {
        int multiply = 1;
        for (int j = 0; j < A.length; j++) {
            if (j == i) {
                multiply *= 1;
            } else {
                multiply *= A[j];
            }

        }
        return multiply;
    }
}

package com.wgs.algorithms.offer;

/**
 * 剑指offer - JZ32
 *
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 *
 * 考点：排序 + 贪心
 */
public class PrintMinNumber_JZ32 {

    public String PrintMinNumber(int [] numbers) {

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                int num1 = Integer.parseInt(numbers[i] + "" + numbers[j]);
                int num2 = Integer.parseInt(numbers[j] + "" + numbers[i]);
                // 即 i1 +  i2 > i2 + i1， 所以需要交换
                if (num1 > num2) {
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }

        StringBuilder sb = new StringBuilder(numbers.length);
        for (int i = 0; i < numbers.length; i++) {
            sb.append(numbers[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] arrs = new int[]{3,32,321};
        System.out.println(new PrintMinNumber_JZ32().PrintMinNumber(arrs));
    }
}

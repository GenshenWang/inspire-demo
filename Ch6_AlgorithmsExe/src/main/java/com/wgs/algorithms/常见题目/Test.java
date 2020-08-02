package com.wgs.algorithms.常见题目;

public class Test {

    private static Test test1 = new Test();
    private static Test test2 = new Test();

    {
        System.out.println("构造块");
    }

    static {
        System.out.println("静态块");
    }

    public static void main(String[] args) {
        Test test = new Test();

        int[][] arr = new int[5][5];
        int t = arr.length;
    }



}

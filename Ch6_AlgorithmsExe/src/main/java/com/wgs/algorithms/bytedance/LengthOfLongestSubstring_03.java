package com.wgs.algorithms.bytedance;

//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
// 示例 1:
//
// 输入: "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
// Related Topics 哈希表 双指针 字符串 Sliding Window
// 👍 4019 👎 0

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public class LengthOfLongestSubstring_03 {

    public int lengthOfLongestSubstring(String s) {

        if (s == null || s.length() <= 0) {
            return 0;
        }

        int maxLen = 0;

        char[] arr = s.toCharArray();
        int p1 = 0;
        int p2 = p1;
        int[] hashTb = new int[256];

        while (p2 < arr.length){

            char c = arr[p2];
            if (hashTb[c] > 0) {
                int len = p2 - p1;
                if (len > maxLen) {
                    maxLen = len;
                }
                p1 = hashTb[c];
                p2 = p1;
                hashTb = new int[256];
            } else {
                // 位置
                hashTb[c] = p2 + 1;
                p2++;
            }


        }

        return maxLen > p2 - p1 ? maxLen : (p2 - p1);
    }


    private volatile boolean flag = true;
    private volatile int index = 0;

    private void print() {

        final Object lock = new Object();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    while (flag) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println("A: " + index++);
                    flag = false;
                    lock.notifyAll();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    while (flag) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println("B: " + index++);
                    flag = true;
                    lock.notifyAll();
                }
            }
        });

        thread1.start();
        thread2.start();
    }

    private static volatile int count = 0;

    public static void main(String[] args) {
        //new LengthOfLongestSubstring_03().lengthOfLongestSubstring("au");

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (count % 2 == 0 && count <= 10) {
                        System.out.println(count);
                        count++;
                    }

                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (count % 2 == 1 && count <= 10) {
                        System.out.println("B: " + count);
                        count++;
                    }

                }
            }
        }).start();
    }

}

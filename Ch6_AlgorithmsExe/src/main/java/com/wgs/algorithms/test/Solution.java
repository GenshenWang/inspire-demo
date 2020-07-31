package com.wgs.algorithms.test;

import java.util.*;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode node = new ListNode(-1);
        ListNode pHead = node;

        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        Queue<Integer> stack1 = new ArrayDeque<>();
        Queue<Integer> stack2 = new ArrayDeque<>();

        int flag = 0;
        while (l1 != null || l2 != null) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            int sum = (num1 + num2 + flag) % 10;
            flag = (num1 + num2 + flag) / 10;
            if (node.val == -1) {
                node.val = sum;
            } else {
                node.next = new ListNode(sum);
                node = node.next;
            }

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }


        if (flag != 0) {
            node.next = new ListNode(flag);
        }

        return pHead;
    }

    private ListNode reverseNode(ListNode pHead) {
        ListNode prev = null;
        ListNode cur = pHead;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;

            prev = cur;
            cur = next;
        }
        return prev;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public int lengthOfLongestSubstring(String s) {

        char[] arr = s.toCharArray();

        // 字符在字符串中位置
        HashMap<Character, Integer> map = new HashMap<>();
        // 每个不重复字符串的长度
        HashMap<String, Integer> sizeMap = new HashMap<>();

        int startIndex = 0;
        int endIndex = 0;
        while (endIndex < arr.length) {
            char temp = arr[endIndex];
            if (!map.containsKey(temp)) {
                map.put(temp, endIndex);
                endIndex++;
            } else {
                recordStrSize(startIndex, endIndex - 1, arr, sizeMap);
                startIndex = map.get(temp) + 1;
                endIndex = startIndex;

                map.clear();
            }

            if (endIndex == arr.length) {
                recordStrSize(startIndex, endIndex - 1, arr, sizeMap);
            }
        }

        return maxSize(sizeMap);
    }

    private int maxSize(HashMap<String, Integer> sizeMap) {
        int len = 0;
        for (Map.Entry entry : sizeMap.entrySet()) {
            int size = (int) entry.getValue();
            if (size > len) {
                len = size;
            }

        }
        return len;
    }

    private void recordStrSize(int startIndex, int endIndex,
                               char[] arr, HashMap<String, Integer> sizeMap) {
        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i <= endIndex; i++) {
            sb.append(arr[i]);
        }
        sizeMap.put(sb.toString(), sb.toString().length());
    }

    public static void main(String[] args) {
        new Solution().lengthOfLongestSubstring("abcabcbb");

        ListNode node1 = new ListNode(1);
        //ListNode node2 = new ListNode(4);
        //ListNode node3 = new ListNode(3);
        //node1.next = node2;
        //node2.next = node3;

        ListNode node4 = new ListNode(9);
        ListNode node5 = new ListNode(9);
        //ListNode node6 = new ListNode(4);
        node4.next = node5;
        //node5.next = node6;

        new Solution().addTwoNumbers(node1, node4);

    }

}

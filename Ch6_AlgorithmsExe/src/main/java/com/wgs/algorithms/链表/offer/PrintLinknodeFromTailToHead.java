package com.wgs.algorithms.链表.offer;

import java.util.ArrayList;

/**
 * @author: wanggenshen
 * @date: 2020/6/2 09:31.
 * @description: 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。 (递归解法)
 */
public class PrintLinknodeFromTailToHead {

    private ArrayList<Integer> result = new ArrayList<>();
    public ArrayList<Integer> printLinknodeFromTailToHead(LinkNode head) {
        if (head == null) {
            return result;
        }
        
        if (head.next != null) {
            printLinknodeFromTailToHead(head.next);
        }

        result.add(head.value);
        return result;
    }


}

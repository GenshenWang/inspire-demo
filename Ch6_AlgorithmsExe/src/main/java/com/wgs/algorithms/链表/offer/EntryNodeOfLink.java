package com.wgs.algorithms.链表.offer;

/**
 * @author: wanggenshen
 * @date: 2020/6/7 17:55.
 * @description: 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 */
public class EntryNodeOfLink {
    public LinkNode EntryNodeOfLoop(LinkNode pHead) {
        if(pHead == null) {
            return null;
        }

        // 1. 获取环中任一节点
        LinkNode circleNode = null;
        LinkNode slow = pHead;
        LinkNode fast = slow.next;
        while(slow != null && fast != null) {
            if(slow == fast) {
                circleNode = slow;
                break;
            }
            slow = slow.next;
            fast = fast.next;
            if(fast != null) {
                fast = fast.next;
            }
        }

        if(circleNode == null) {
            return null;
        }

        // 2. 计算环长度
        int circleLen = 1;
        LinkNode tmpNode = circleNode.next;
        while(tmpNode.value != circleNode.value) {
            tmpNode = tmpNode.next;
            ++circleLen;
        }

        // 3. 双指针
        LinkNode p1 = pHead;
        LinkNode p2 = pHead;
        // p1 先走circleLen步
        for(int i = 0; i < circleLen; i++) {
            p1 = p1.next;
        }
        while(p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;

    }
}

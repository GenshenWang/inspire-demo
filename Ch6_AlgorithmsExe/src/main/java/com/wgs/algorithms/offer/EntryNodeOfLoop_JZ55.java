package com.wgs.algorithms.offer;

import com.wgs.algorithms.bytedance.ListNode;

import java.util.HashSet;
import java.util.Set;

public class EntryNodeOfLoop_JZ55 {

    /**
     * 遍历一遍链表
     * 时间复杂度：O(n) + O(k)
     * 空间复杂度：O(k)
     * @param pHead
     * @return
     */
    public ListNode EntryNodeOfLoop(ListNode pHead){
        if(pHead  == null) {
            return null;
        }
        ListNode fast = pHead.next;
        ListNode slow = pHead;
        ListNode circleNode = null;
        // 寻找环内某个节点
        while(fast != null && fast.next != null) {
            if(fast == slow) {
                circleNode = fast;
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        if(circleNode == null) {
            return null;
        }

        fast = circleNode.next;
        Set<ListNode> set = new HashSet<>();
        set.add(circleNode);
        while(fast != circleNode) {
            set.add(fast);
            fast = fast.next;
        }

        fast = pHead;
        while(fast != null) {
            if(set.contains(fast)){
                System.out.println(fast);
                return fast;
            }
            fast = fast.next;
        }

        return null;

    }

    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(9);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node3;
        new EntryNodeOfLoop_JZ55().EntryNodeOfLoop(head);
    }
}

package com.wgs.algorithms.offer;


/**
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class DeleteDuplication_JZ56 {

    public ListNode deleteDuplication(ListNode pHead){
        if (pHead ==null) {
            return null;
        }
        ListNode preNode = new ListNode(-1);
        preNode.next = pHead;
        ListNode pNode = preNode;

        ListNode curNode = pHead;
        while (curNode != null) {
            ListNode next = curNode.next;
            if (next == null || curNode.val != next.val) {
                preNode.next = curNode;
                preNode = curNode;
                curNode = next;
            } else if (curNode.val == next.val) {
                int val = curNode.val;
                while (curNode != null && curNode.val == val) {
                    curNode = curNode.next;
                }
                preNode.next = curNode;
            }
        }


        return  pNode.next;
    }

    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        /*ListNode pHead = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(4);
        ListNode node7 = new ListNode(5);*/

        ListNode pHead = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(1);
        ListNode node6 = new ListNode(1);
        ListNode node7 = new ListNode(1);

        pHead.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        new DeleteDuplication_JZ56().deleteDuplication(pHead);
    }
}

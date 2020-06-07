package com.wgs.algorithms.链表.offer;

/**
 * @author: wanggenshen
 * @date: 2020/6/7 21:39.
 * @description: 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 *                  例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class DeleteDuplicateNode {

    public LinkNode deleteDuplication(LinkNode pHead) {
        if(pHead == null) {
            return null;
        }
        LinkNode head = new LinkNode(-1);

        head.next = pHead;
        LinkNode pre = head;
        LinkNode curNode = pHead;
        while(curNode != null && curNode.next != null) {
            LinkNode nextNode = curNode.next;
            if(nextNode.value == curNode.value) {
                int val = curNode.value;
                while(curNode != null && curNode.value == val) {
                    curNode = curNode.next;
                }
                pre.next = curNode;
            } else {
                pre = curNode;
                curNode = curNode.next;
            }

        }

        return head.next;
    }

    public static void main(String[] args) {
        LinkNode node1 = new LinkNode(1);
        LinkNode node2 = new LinkNode(2);
        LinkNode node3 = new LinkNode(3);
        LinkNode node4 = new LinkNode(3);
        LinkNode node5 = new LinkNode(4);
        LinkNode node6 = new LinkNode(4);
        LinkNode node7 = new LinkNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        new DeleteDuplicateNode().deleteDuplication(node1);


    }
}

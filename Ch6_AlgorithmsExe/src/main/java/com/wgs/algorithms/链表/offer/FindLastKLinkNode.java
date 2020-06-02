package com.wgs.algorithms.链表.offer;

/**
 * @author: wanggenshen
 * @date: 2020/6/2 09:34.
 * @description: 输入一个链表，输出该链表中倒数第k个结点。
 */
public class FindLastKLinkNode {

    public LinkNode FindKthToTail(LinkNode head, int k) {
        if(head == null) {
            return null;
        }

        int len = 0;
        LinkNode temp = head;
        while(temp != null) {
            len++;
            temp = temp.next;
        }
        if(k > len) {
            return null;
        }
        LinkNode first = head;
        LinkNode last = head;
        for(int i = 1; i <= k; i++){
            last = last.next;
        }

        while(last != null) {
            last = last.next;
            first = first.next;
        }
        return first;
    }

    public static void main(String[] args) {
        LinkNode node1 = new LinkNode(1);
        LinkNode node2 = new LinkNode(2);
        LinkNode node3 = new LinkNode(3);
        LinkNode node4 = new LinkNode(4);
        LinkNode node5 = new LinkNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

        System.out.println(node1);
        LinkNode node = new FindLastKLinkNode().FindKthToTail(node1, 1);
        System.out.println(node);
    }
}

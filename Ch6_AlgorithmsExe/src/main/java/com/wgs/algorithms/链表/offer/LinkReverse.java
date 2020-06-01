package com.wgs.algorithms.链表.offer;

/**
 * @author: wanggenshen
 * @date: 2020/6/1 16:59.
 * @description: 输入一个链表，反转链表后，输出新链表的表头。
 */
public class LinkReverse {


    public static LinkNode ReverseList(LinkNode head) {

        if (head == null) {
            return head;
        }

        LinkNode prev = null;
        LinkNode next = null;
        while (head != null) {

            // next指针指向后继结点
            next = head.next;
            // 指向前一个结点
            head.next = prev;

            // prev指针移动, 指向下个结点
            prev = head;
            head = next;
        }

        return prev;
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
        LinkNode node = ReverseList(node1);
        System.out.println(node);
    }

}

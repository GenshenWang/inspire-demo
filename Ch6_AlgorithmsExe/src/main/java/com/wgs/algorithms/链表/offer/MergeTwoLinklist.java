package com.wgs.algorithms.链表.offer;

/**
 * @author: wanggenshen
 * @date: 2020/6/2 13:10.
 * @description: 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则
 */
public class MergeTwoLinklist {

    /**
     * 递归版本
     *
     * @param head1
     * @param head2
     * @return
     */
    public LinkNode merge(LinkNode head1, LinkNode head2) {

        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        LinkNode mergeNode;
        if (head1.value < head2.value) {
            mergeNode = head1;
            mergeNode.next = merge(head1.next, head2);
        } else {
            mergeNode = head2;
            mergeNode.next = merge(head1, head2.next);
        }

        return mergeNode;

    }


    public LinkNode merge2(LinkNode head1, LinkNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        LinkNode mergeNode = new LinkNode(-1);
        LinkNode pHead = mergeNode;
        while (head1 != null && head2 != null) {
            if (head1.value < head2.value) {
                mergeNode.next = head1;
                mergeNode = mergeNode.next;
                head1 = head1.next;
            } else {
                mergeNode.next = head2;
                mergeNode = mergeNode.next;
                head2 = head2.next;
            }
        }

        if (head1 != null) {
            mergeNode.next = head1;
        } else if (head2 != null) {
            mergeNode.next = head2;
        }

        return pHead;
    }

    public static void main(String[] args) {
        LinkNode head11 = new LinkNode(1);
        LinkNode node12 = new LinkNode(3);
        LinkNode node13 = new LinkNode(5);
        head11.next = node12;
        node12.next = node13;

        LinkNode head21 = new LinkNode(2);
        LinkNode node22 = new LinkNode(4);
        LinkNode node23 = new LinkNode(7);
        head21.next = node22;
        node22.next = node23;

        new MergeTwoLinklist().merge2(head11, head21);

    }

}

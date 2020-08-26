package com.wgs.algorithms.test;

public class LinkedStack<E> {
    private class LinkStackNode {
        private E data;
        private LinkStackNode next;

        public LinkStackNode(E data) {
            this.data = data;
        }

        public LinkStackNode(E data, LinkStackNode next) {
            this.data = data;
            this.next = next;
        }
    }

    private LinkStackNode head;

    public boolean push(E node) {
        LinkStackNode pushNode = new LinkStackNode(node, null);
        if (head == null) {
            head = pushNode;
        } else {
            head.next = pushNode;
        }

        return true;
    }

    public E pop() {
        if (head == null) {
            return null;
        }

        LinkStackNode temp = head;
        while (temp != null && temp.next != null) {
            temp = temp.next;
        }

        // 后进先出，直接返回链表尾部即可
        Object obj = temp.data;
        temp.next = null;
        return (E) obj;
    }
}

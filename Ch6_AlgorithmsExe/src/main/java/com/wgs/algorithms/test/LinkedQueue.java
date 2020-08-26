package com.wgs.algorithms.test;

public class LinkedQueue<E> {

    private LinkNode head;
    private LinkNode tail;
    private int count;

    public LinkedQueue() {
        this.head = null;
        this.tail = null;
    }


    public boolean enQueue(E item) {
        LinkNode node = new LinkNode(item);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }

        return true;
    }

    private E deQueue() {
        if (head == null) {
            return null;
        }
        Object val = head.val;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return (E) val;
    }


    static class LinkNode<E> {
        private E val;
        private LinkNode next;

        public LinkNode(E val) {
            this.val = val;
        }

        public LinkNode(E val, LinkNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

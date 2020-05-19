package com.wgs.algorithms.队列;

/**
 * @author: wanggenshen
 * @date: 2020/5/19 19:45.
 * @description: 队列实现二: 链表
 */
public class LinkQueue<E> implements MyQueue<E> {

    private class LinkNode {
        private Object data;
        private LinkNode next;

        public LinkNode(Object data, LinkNode next) {
            this.data = data;
            this.next = next;
        }
    }


    private LinkNode headNode;
    private LinkNode tailNode;

    /**
     * 入队时间复杂度为O(1)
     *
     * @param node
     */
    @Override
    public void enqueue(E node) {

        LinkNode enqueueNode = new LinkNode(node, null);
        if (tailNode == null) {
            headNode = enqueueNode;
            tailNode = enqueueNode;
        } else {
            // 当前节点指向新增的节点
            tailNode.next = enqueueNode;
            // tail指针移向新增的节点
            tailNode = tailNode.next;
        }
    }

    @Override
    public E dequeue() {
        if (headNode == null) {
            return null;
        }
        E obj = (E) headNode.data;
        headNode = headNode.next;
        return obj;
    }


    public static void main(String[] args) {
        LinkQueue<Integer> queue = new LinkQueue<>();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println(queue.dequeue());

        queue.enqueue(4);
        queue.enqueue(5);
        System.out.println(queue.dequeue());

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

    }
}

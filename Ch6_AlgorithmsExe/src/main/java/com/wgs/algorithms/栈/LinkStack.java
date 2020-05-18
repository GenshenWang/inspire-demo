package com.wgs.algorithms.栈;

/**
 * @author: wanggenshen
 * @date: 2020/5/18 20:22.
 * @description: 栈的实现二: 链式栈
 */
public class LinkStack<E> {

    private class LinkNode {
        private E data;
        private LinkNode next;

        public LinkNode(E data, LinkNode next) {
            this.data = data;
            this.next = next;
        }

    }

    private LinkNode headNode = null;

    public void push(E node) {
        LinkNode pushNode = new LinkNode(node, null);
        // 第一次添加
        if (headNode == null) {
            headNode = pushNode;
        } else {
            // 指向前一个node
            pushNode.next = headNode;
            headNode = pushNode;
        }
    }

    public E pop() {
        if (headNode == null) {
            return null;
        }

        E obj = headNode.data;
        headNode = headNode.next;
        return obj;
    }


    public static void main(String[] args) {
        LinkStack<String> linkStack = new LinkStack<>();
        linkStack.push("a");
        linkStack.push("b");
        linkStack.push("c");

        System.out.println(linkStack.pop());

        linkStack.push("d");
        System.out.println(linkStack.pop());

        linkStack.push("a");

        System.out.println(linkStack.pop());
        System.out.println(linkStack.pop());

        System.out.println(linkStack.pop());
        System.out.println(linkStack.pop());
    }
}

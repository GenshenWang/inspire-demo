package com.wgs.algorithms.队列;

import com.wgs.algorithms.树.TreePrint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class LRUCache2 {

    private int capacity;
    private int count;
    private Map<Integer, Node> localCache;

    private Node head;
    private Node tail;

    public LRUCache2(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.localCache = new HashMap<>(16);

        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     *
     * @param key
     * @return
     */
    public int get(int key) {
        Node node = localCache.get(key);
        if (node == null) {
            System.out.println("GET++++" + -1);
            return -1;
        }

        // 移动到头节点
        deleteNodeFromList(node);
        addToListHead(node);
        System.out.println("GET++++"+ node.value);
        return node.value;
    }



            /**
             * 时间复杂度：O(1)
             * 空间复杂度：O(1)
             *
             * @param key
             * @param value
             */
    public void put(int key, int value) {
        Node node = localCache.get(key);
        if (node == null) {
            // 已满，则删除
            if (count == capacity) {
                Node tempTailNode = tail.prev;
                deleteNodeFromList(tempTailNode);
                count--;
                localCache.remove(tempTailNode.key);
            }

            // 不存在，放到头部
            node = new Node(key, value);
            addToListHead(node);
            localCache.put(node.key, node);
            count++;
        } else {
            // 存在，移动到头部
            deleteNodeFromList(node);
            node.value = value;
            addToListHead(node);

        }
    }

    private void addToListHead(Node node) {
        Node next = head.next;
        next.prev = node;
        node.next = next;

        head.next = node;
        node.prev = head;
    }

    private void deleteNodeFromList(Node node) {
        Node preNode = node.prev;
        Node nextNode = node.next;
        preNode.next = nextNode;
        nextNode.prev = preNode;
        node.prev = null;
        node.next = null;


    }

    static class Node {
        private int key;
        private int value;
        private Node prev;
        private Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }
    }

    public static void main(String[] args) {
        LRUCache2 lruCache2 = new LRUCache2(2);
        /*lruCache2.put(1, 1);
        lruCache2.put(2, 2);
        lruCache2.get(1);
        lruCache2.put(3, 3);
        lruCache2.get(2);
        lruCache2.put(4, 4);
        lruCache2.get(1);
        lruCache2.get(3);
        lruCache2.get(4);*/

        lruCache2.put(2, 1);
        lruCache2.put(2, 2);
        lruCache2.get(2);
        lruCache2.put(1, 3);
        lruCache2.put(4, 1);
        lruCache2.get(2);
    }
}

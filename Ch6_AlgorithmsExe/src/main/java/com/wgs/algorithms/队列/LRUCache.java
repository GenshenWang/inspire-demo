package com.wgs.algorithms.队列;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private int capacity;
    private int count;
    private Map<K, Node<K, V>> localCache;

    private Node<K, V> head;
    private Node<K, V> tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.localCache = new HashMap<>(16);

        // 哨兵模式，减少头、尾结点为空判断
        Node headNode = new Node(null, null);
        Node tailNode = new Node(null, null);
        headNode.next = tailNode;
        tailNode.prev = headNode;

        this.head = headNode;
        this.tail = tailNode;
    }

    /**
     * 放入的node存在，移动到头节点；
     * 不存在，（满，移除1个节点），再直接添加新节点
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        Node<K, V> node = localCache.get(key);
        if (node == null) {
            if (capacity == count) {
                // 移除尾部节点
                Node tailNode = tail.prev;

                removeNodeFromList(tailNode);

                localCache.remove(tailNode.key);
                count--;
            }
            node = new Node<>(key, value);

            // 添加头结点（最新访问的放头部）
            addToHead(node);

            localCache.put(key, node);
            count++;
        } else {
            // 移动到头结点（先删除，再添加到头结点）
            removeNodeFromList(node);
            node.value = value;
            addToHead(node);
        }
    }

    public Node<K, V> get(K key) {
        Node<K, V> node = localCache.get(key);
        if (node != null) {
            removeNodeFromList(node);
            addToHead(node);
        }
        return node;
    }

    private void addToHead(Node<K, V> node) {
        Node next = head.next;
        next.prev = node;
        node.next = next;
        head.next = node;
        node.prev = next;
    }

    private void removeNodeFromList(Node tailNode) {
        Node prevNode = tailNode.prev;
        Node nextNode = tailNode.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        tailNode.prev = null;
        tailNode.next = null;
    }

    static class Node<K, V> {
        K key;
        V value;
        Node prev;
        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

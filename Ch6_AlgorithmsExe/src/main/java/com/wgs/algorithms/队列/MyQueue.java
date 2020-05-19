package com.wgs.algorithms.队列;

/**
 * @author: wanggenshen
 * @date: 2020/5/19 13:04.
 * @description: 队列的两种实现: 数组和链表
 */
public interface MyQueue<E> {
    /**
     * 入队操作
     *
     * @param node
     */
    void enqueue(E node);

    /**
     * 出队操作
     *
     * @return
     */
    E dequeue();
}

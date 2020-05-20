package com.wgs.algorithms.队列;

/**
 * @author: wanggenshen
 * @date: 2020/5/19 20:16.
 * @description: 循环队列的实现
 */
public class CircularQueue<E> implements MyQueue<E>{

    private Object[] queue;
    private int size;
    private int headIndex;
    private int tailIndex;

    /**
     * 初始化一个有大小的队列
     *
     * @param size
     */
    public CircularQueue(int size) {
        queue = new Object[size];
        this.size = size;
    }

    @Override
    public void enqueue(E node) {

        // 队列已满
        if ((tailIndex + 1) % size == headIndex) {
            throw new RuntimeException("Exceed queue size");
        }

        queue[tailIndex] = node;
        // 当超过一圈的时候, 需要置0重新计算
        tailIndex = (tailIndex + 1) % size;
    }

    @Override
    public E dequeue() {
        // 队列为空
        if (headIndex == tailIndex) {
            return null;
        }
        E obj = (E) queue[headIndex];
        headIndex = (headIndex + 1) % size;
        return obj;
    }
}

package com.wgs.algorithms.队列;

/**
 * @author: wanggenshen
 * @date: 2020/5/19 13:06.
 * @description: 队列实现1: 数组(优化版本:双指针)
 */
public class ArrayQueue2<E> implements MyQueue<E> {

    private Object[] queue;
    private int size;
    private int count;

    private int headIndex = 0;
    private int tailIndex = 0;

    public ArrayQueue2(int queueSize) {
        queue = new Object[queueSize];
        this.size = queueSize;
        this.count = 0;
    }

    /**
     * 不移动数据, 入队时, 时间复杂度为O(1), 弊端就是造成内存浪费;
     * 解决办法:
     *     当tail指针移动到尾部的时候,清空前面的无效内存。此时最好的时间复杂度为O(1), 最差为O(n)
     *
     *
     * @param node
     */
    @Override
    public void enqueue(E node) {
        if (tailIndex >= size) {
            // headIndex不在0的位置, 说明有无效内存, 需清除
            if (headIndex > 0) {
                clearInvalidCache();
            } else {
                throw new RuntimeException("Exceed queue size");
            }
        }

        queue[tailIndex] = node;
        tailIndex++;
    }

    /**
     * 清除无效内存。 如size =  5的队列:  * * 3 4 5 -> 3 4 5 * * (整体移动位置为, tail - head)
     */
    private void clearInvalidCache() {
        System.arraycopy(queue, headIndex, queue, 0, tailIndex - headIndex);
        // 更新指针

        tailIndex -= headIndex;
        headIndex = 0;
    }

    /**
     * 双指针: 出队时, 时间复杂度为O(1)
     *
     * @return
     */
    @Override
    public E dequeue() {
        // 均为0, 或者均为最后一个值, 队列为空
        if (headIndex == tailIndex) {
            return null;
        }

        E obj = (E) queue[headIndex];
        headIndex++;
        return obj;
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new ArrayQueue2<>(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        // System.out.println(queue.dequeue());

        //System.out.println(queue.dequeue());
    }
}

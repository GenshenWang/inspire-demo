package com.wgs.codedesign.迭代器模式.迭代器模式实现_List迭代器;

/**
 * @author: wanggenshen
 * @date: 2020/4/20 09:33.
 * @description: 迭代器接口
 */
public interface MyIterator<E> {

    /**
     * 是否有一下个元素
     *
     * @return
     */
    boolean hasNext();

    /**
     * 查看下一个元素
     */
    void next();

    /**
     * 获取当前元素
     *
     * @return
     */
    E getCurrentNode();

}

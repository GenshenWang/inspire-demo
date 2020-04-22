package com.wgs.codedesign.迭代器模式.迭代器模式实现_List迭代器;

/**
 * @author: wanggenshen
 * @date: 2020/4/21 22:43.
 * @description: 抽象类, 添加modCount
 */
public abstract class AbstractList<E> implements MyList<E> {

    protected int modCount;

    public int getModCount() {
        return modCount;
    }


}

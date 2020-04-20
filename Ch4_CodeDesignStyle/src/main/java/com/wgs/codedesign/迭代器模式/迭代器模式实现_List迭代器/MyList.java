package com.wgs.codedesign.迭代器模式.迭代器模式实现_List迭代器;

/**
 * @author: wanggenshen
 * @date: 2020/4/20 09:32.
 * @description: 自定义集合
 */
public interface MyList<E> {
    void add(E e);

    int size();

    E get(int index);

    MyIterator<E> iterator();

}

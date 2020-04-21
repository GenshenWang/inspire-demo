package com.wgs.codedesign.迭代器模式.迭代器模式实现_List迭代器;

import java.util.ConcurrentModificationException;

/**
 * @author: wanggenshen
 * @date: 2020/4/20 09:35.
 * @description: 数组迭代器
 */
public class MyArrayIterator<E> implements MyIterator<E> {

    /**
     * 游标
     */
    private int cursor;

    private int expectedModCount;

    private MyArrayList<E> arrayList;

    public MyArrayIterator(MyArrayList<E> arrayList) {
        this.cursor = 0;
        this.arrayList = arrayList;
        this.expectedModCount = arrayList.modCount;

    }


    @Override
    public boolean hasNext() {
        checkComodification();
        return cursor < arrayList.size();
    }

    @Override
    public void next() {
        checkComodification();
        this.cursor++;
    }

    private void checkComodification() {
        if (arrayList.getModCount() != expectedModCount) {
            throw new ConcurrentModificationException("There some ele changed while list them");
        }
    }

    @Override
    public E getCurrentNode() {
        checkComodification();
        if (cursor >= arrayList.size()) {
            throw new RuntimeException("Index exceed range of list");
        }
        return arrayList.get(cursor);
    }
}

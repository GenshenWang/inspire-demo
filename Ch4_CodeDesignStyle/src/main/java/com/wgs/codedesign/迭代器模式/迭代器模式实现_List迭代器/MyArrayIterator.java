package com.wgs.codedesign.迭代器模式.迭代器模式实现_List迭代器;

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

    private MyList<E> arrayList;

    public MyArrayIterator(MyList<E> arrayList) {
        this.cursor = 0;
        this.arrayList = arrayList;
    }


    @Override
    public boolean hasNext() {
        return cursor < arrayList.size();
    }

    @Override
    public void next() {
        this.cursor++;
    }

    @Override
    public E getCurrentNode() {
        if (cursor >= arrayList.size()) {
            throw new RuntimeException("Index exceed range of list");
        }
        return arrayList.get(cursor);
    }
}

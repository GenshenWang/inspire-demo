package com.wgs.codedesign.迭代器模式.迭代器模式实现_List迭代器;

/**
 * @author: wanggenshen
 * @date: 2020/4/20 09:36.
 * @description: 数组集合实现
 */
public class MyArrayList<E> implements MyList<E> {

    private Object[] elementData;

    /**
     * The real size of elements that list contains
     */
    private int size;

    public MyArrayList() {
        this.elementData = new Object[10];
    }

    public MyArrayList(int size) {
        this.elementData = new Object[size];
    }

    @Override
    public void add(E e) {
        ensureElementDataSize(size + 1);
        elementData[size++] = e;
    }

    private void ensureElementDataSize(int size) {
        if (size > elementData.length) {
            grow();
        }
    }

    /**
     * 扩容, 每次加10
     */
    private void grow() {
        Object[] newElementData = new Object[elementData.length + 10];
        for (int i = 0; i < elementData.length; i++) {
            newElementData[i] = elementData[i];
        }
        elementData = newElementData;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return (E) elementData[index];
    }

    private void rangeCheck(int index) {
        if (index >= size) {
            throw new RuntimeException("Exceed list size");
        }
    }

    @Override
    public MyIterator<E> iterator() {
        return new MyArrayIterator<E>(this);
    }
}

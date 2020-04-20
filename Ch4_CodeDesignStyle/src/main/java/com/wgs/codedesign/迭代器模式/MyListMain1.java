package com.wgs.codedesign.迭代器模式;

import com.wgs.codedesign.迭代器模式.迭代器模式实现_List迭代器.MyArrayList;
import com.wgs.codedesign.迭代器模式.迭代器模式实现_List迭代器.MyIterator;
import com.wgs.codedesign.迭代器模式.迭代器模式实现_List迭代器.MyList;

/**
 * @author: wanggenshen
 * @date: 2020/4/20 10:42.
 * @description: XXX
 */
public class MyListMain1 {

    public static void main(String[] args) {
        MyList<String> list = new MyArrayList<>();
        list.add("aaa");
        list.add("ccc");
        list.add("bbb");

        MyIterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String ele = iterator.getCurrentNode();
            System.out.println(ele);
            iterator.next();
        }
    }
}

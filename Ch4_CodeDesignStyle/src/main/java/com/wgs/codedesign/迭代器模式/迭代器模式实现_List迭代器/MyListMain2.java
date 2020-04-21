package com.wgs.codedesign.迭代器模式.迭代器模式实现_List迭代器;

/**
 * @author: wanggenshen
 * @date: 2020/4/20 10:42.
 * @description: XXX
 */
public class MyListMain2 {

    public static void main(String[] args) {
        MyList<String> list = new MyArrayList<>();
        list.add("aaa");
        list.add("ccc");
        list.add("bbb");

        MyIterator<String> iterator = list.iterator();
        MyIterator<String> iterator2 = list.iterator();


        // 遍历的时候添加元素
        iterator.next();
        System.out.println(iterator.getCurrentNode());

        list.add("eee");

        iterator2.next();
        /*iterator.next();
        System.out.println(iterator.getCurrentNode());*/



    }
}

package com.wgs.codedesign.原型模式;

import java.awt.print.Book;
import java.util.HashMap;

/**
 * @author: wanggenshen
 * @date: 2020/3/26 22:19.
 * @description: XXX
 */
public class Main {

    public static void main(String[] args) {
        HashMap<String, Book> map = new HashMap<>();
        Book book = new Book();
        book.setName("a");
        map.put("a", book);
        System.out.println(map.get("a"));

        map.remove("a");

        book = new Book();
        book.setName("b");
        map.put("a", book);
        System.out.println(map.get("a"));
    }

    static class Book {
        private String name;

        public Book() {
        }

        public Book(String name) {
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

package com.wgs.codedesign.快照模式.v2;

/**
 * @author: wanggenshen
 * @date: 2020/4/29 23:02.
 * @description: XXX
 */
public class Snapshot {

    private String inputText;

    public Snapshot(String inputText) {
        this.inputText = inputText;
    }

    public String getText(){
        return inputText;
    }
}

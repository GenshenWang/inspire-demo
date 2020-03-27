package com.wgs.codedesign.原型模式;

/**
 * @author: wanggenshen
 * @date: 2020/3/26 18:48.
 * @description: XXX
 */
public class SearchWord {

    private String keyWord;
    private long count;
    private long updateTime;



    public String getKeyWord() {
        return keyWord;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getCount() {

        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}

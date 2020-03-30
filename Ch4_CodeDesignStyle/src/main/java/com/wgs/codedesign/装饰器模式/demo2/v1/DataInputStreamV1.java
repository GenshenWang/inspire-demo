package com.wgs.codedesign.装饰器模式.demo2.v1;

import com.wgs.codedesign.装饰器模式.demo2.MyInputStream;

/**
 * @author: wanggenshen
 * @date: 2020/3/30 13:32.
 * @description: XXX
 */
public class DataInputStreamV1 extends MyInputStream{

    protected volatile MyInputStream inputStream;

    public DataInputStreamV1(MyInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public int bufferRead() {
        System.out.println("Data read input stream.");
        return 0;
    }

    @Override
    public int read() {
        return inputStream.read();
    }

    @Override
    public int reset() {
        return inputStream.reset();
    }
}

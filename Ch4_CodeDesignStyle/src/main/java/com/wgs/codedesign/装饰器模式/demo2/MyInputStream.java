package com.wgs.codedesign.装饰器模式.demo2;

import java.io.IOException;

/**
 * @author: wanggenshen
 * @date: 2020/3/30 13:26.
 * @description: XXX
 */
public abstract class MyInputStream {

    public abstract int read();

    public abstract int reset();

    public void read(String s) {
        System.out.println("...");
    }


    public int read(byte b[]) throws IOException {
        return read(b, 0, b.length);
    }

    public int read(byte b[], int off, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }

        int c = read();
        if (c == -1) {
            return -1;
        }
        b[off] = (byte)c;

        int i = 1;
        for (; i < len ; i++) {
            c = read();
            if (c == -1) {
                break;
            }
            b[off + i] = (byte)c;
        }
        return i;
    }

}

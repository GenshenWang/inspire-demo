package com.wgs.codedesign.适配器模式.exe2_多个接口统一;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/3/31 22:36.
 * @description: XXX
 */
public class Main {

    public static void main(String[] args) {
        List<ISensitiveWordsFilter> filters = Lists.newArrayList();
        filters.add(new ASensitiveWordsFilterAdaptor(new ASensitiveWordsFilter()));
        filters.add(new BSensitiveWordsFilterAdaptor(new BSensitiveWordsFilter()));

        String text = "我爱人民币, 我爱usa性感的女生";
        for (ISensitiveWordsFilter filter : filters) {
            text = filter.filter(text);
        }
        System.out.println(text);
    }
}

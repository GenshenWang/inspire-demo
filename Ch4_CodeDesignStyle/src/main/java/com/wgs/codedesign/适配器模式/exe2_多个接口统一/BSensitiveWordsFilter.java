package com.wgs.codedesign.适配器模式.exe2_多个接口统一;

/**
 * @author: wanggenshen
 * @date: 2020/3/31 22:32.
 * @description: XXX
 */
public class BSensitiveWordsFilter {

    public String filterWords(String text) {
        if (text.contains("usa")) {
            return text.replaceAll("usa", "***");
        }
        return text;
    }

}

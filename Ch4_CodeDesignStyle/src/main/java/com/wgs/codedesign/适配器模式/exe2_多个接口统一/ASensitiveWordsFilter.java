package com.wgs.codedesign.适配器模式.exe2_多个接口统一;

/**
 * @author: wanggenshen
 * @date: 2020/3/31 22:32.
 * @description: XXX
 */
public class ASensitiveWordsFilter {
    public String filterSexyWords(String text) {
        if (text.contains("性感")) {
            return text.replaceAll("性感", "**");
        }
        return text;
    }

    public String filterPoliticalWords(String text) {
        if (text.contains("人民币")) {
            return text.replaceAll("人民币", "***");
        }
        return text;
    }
}

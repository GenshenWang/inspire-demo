package com.wgs.codedesign.适配器模式.exe2_多个接口统一;

/**
 * @author: wanggenshen
 * @date: 2020/3/31 22:34.
 * @description: XXX
 */
public class ASensitiveWordsFilterAdaptor implements ISensitiveWordsFilter {

    private ASensitiveWordsFilter aSensitiveWordsFilter;

    public ASensitiveWordsFilterAdaptor(ASensitiveWordsFilter aSensitiveWordsFilter) {
        this.aSensitiveWordsFilter = aSensitiveWordsFilter;
    }

    @Override
    public String filter(String text) {
        text = aSensitiveWordsFilter.filterSexyWords(text);
        return aSensitiveWordsFilter.filterPoliticalWords(text);
    }
}

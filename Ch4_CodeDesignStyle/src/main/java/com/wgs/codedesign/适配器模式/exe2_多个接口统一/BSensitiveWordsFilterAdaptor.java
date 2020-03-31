package com.wgs.codedesign.适配器模式.exe2_多个接口统一;

/**
 * @author: wanggenshen
 * @date: 2020/3/31 22:32.
 * @description: XXX
 */
public class BSensitiveWordsFilterAdaptor implements ISensitiveWordsFilter{

    private BSensitiveWordsFilter bSensitiveWordsFilter;

    public BSensitiveWordsFilterAdaptor(BSensitiveWordsFilter bSensitiveWordsFilter) {
        this.bSensitiveWordsFilter = bSensitiveWordsFilter;
    }

    @Override
    public String filter(String text) {
        return bSensitiveWordsFilter.filterWords(text);
    }
}

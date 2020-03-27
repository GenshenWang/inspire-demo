package com.wgs.codedesign.原型模式;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: wanggenshen
 * @date: 2020/3/26 18:47.
 * @description: 内存中才数据必须是同一个版本的
 *               直接new所有的对象, 然后赋值给原对象引用
 */
public class PrototypeDemo2 {
    private ConcurrentHashMap<String, SearchWord> currentKeywords = new ConcurrentHashMap<>();

    public void refresh() {
        ConcurrentHashMap<String, SearchWord> newKeywords = new ConcurrentHashMap<>();

        List<SearchWord> toBeUpdatedSearchWords = getAllSearchWords();
        if (CollectionUtils.isEmpty(toBeUpdatedSearchWords)) {
            return;
        }
        for (SearchWord searchWord: toBeUpdatedSearchWords) {
            newKeywords.put(searchWord.getKeyWord(), searchWord);
        }

        currentKeywords = newKeywords;
    }

    private List<SearchWord> getAllSearchWords() {
        // DB查询时间大于 lastUpdateTime 的所有数据
        return null;
    }
}

package com.wgs.codedesign.原型模式;

import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/3/26 18:47.
 * @description: DB有新更新的搜索数据, 立即更新内存
 */
public class PrototypeDemo1 {
    private HashMap<String, SearchWord> currentKeywords = new HashMap<>();

    private long lastUpdateTime = -1;

    public void refresh() {
        List<SearchWord> toBeUpdatedSearchWords = getSearchWords(lastUpdateTime);
        if (CollectionUtils.isEmpty(toBeUpdatedSearchWords)) {
            return;
        }
        long maxNewUpdatedTime = lastUpdateTime;
        for (SearchWord searchWord: toBeUpdatedSearchWords) {
            if (searchWord.getUpdateTime() > maxNewUpdatedTime) {
                maxNewUpdatedTime = searchWord.getUpdateTime();
            }
            if (currentKeywords.containsKey(searchWord.getKeyWord())) {
                currentKeywords.replace(searchWord.getKeyWord(), searchWord);
            } else {
                currentKeywords.put(searchWord.getKeyWord(), searchWord);
            }
        }

        lastUpdateTime = maxNewUpdatedTime;
    }

    private List<SearchWord> getSearchWords(long lastUpdateTime) {
        // DB查询时间大于 lastUpdateTime 的所有数据
        return null;
    }
}

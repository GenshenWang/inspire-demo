package com.wgs.codedesign.原型模式;

import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/3/26 19:02.
 * @description: 如何快速copy hash列表
 *               (1)先浅copy
 *               (2)对于需要更新或者新增的数据, 直接用新的对象替换之前的对象
 */
public class PrototypeDemo_shadowAndDeepCopy {
    private HashMap<String, SearchWord> currentKeywords = new HashMap<>();

    private long lastUpdateTime = -1;

    public void refresh() {
        // copy一份数据, 只更新当前数据
        HashMap<String, SearchWord> shadowCurrentKeywords = (HashMap<String, SearchWord>) currentKeywords.clone();

        List<SearchWord> toBeUpdatedSearchWords = getSearchWords(lastUpdateTime);
        if (CollectionUtils.isEmpty(toBeUpdatedSearchWords)) {
            return;
        }
        long maxNewUpdatedTime = lastUpdateTime;
        for (SearchWord searchWord : toBeUpdatedSearchWords) {
            if (searchWord.getUpdateTime() > maxNewUpdatedTime) {
                maxNewUpdatedTime = searchWord.getUpdateTime();
            }
            if (shadowCurrentKeywords.containsKey(searchWord.getKeyWord())) {
                shadowCurrentKeywords.remove(searchWord.getKeyWord());
            }
            shadowCurrentKeywords.put(searchWord.getKeyWord(), searchWord);
        }

        lastUpdateTime = maxNewUpdatedTime;
        currentKeywords = shadowCurrentKeywords;
    }

    private List<SearchWord> getSearchWords(long lastUpdateTime) {
        // DB查询时间大于 lastUpdateTime 的所有数据
        return null;
    }
}

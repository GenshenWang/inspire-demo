package com.wgs.algorithms.排序;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 字典中出现次数最多的10个单词
 */
public class HeadpSort2 {

    public List<String> getTopKWords(String[] words, int k) {
        HashMap<String, Integer> counter = new HashMap(k);

        // 初始化
        List<WordCountDTO> wordCountList = new ArrayList<>();
        int index = 0;
        while (wordCountList.size() < k) {
            String word = words[index++];
            int count = !counter.containsKey(word) ? 1 : counter.get(word) + 1;
            if (counter.containsKey(word)) {
                wordCountList.stream().forEach(wordCountDTO -> {
                    if (word.equals(wordCountDTO.getWord())) {
                        wordCountDTO.setCount(count);
                    }
                });
            } else {
                wordCountList.add(new WordCountDTO(word, count));
            }
            counter.put(word, count);
        }

        // 构建小顶堆
        for (int i = wordCountList.size() / 2 - 1; i >= 0; i--) {
            heapAdjust(wordCountList, i, wordCountList.size());
        }

        // 继续调整
        for (int i = index; i < words.length; i++) {
            String word = words[i];
            int count = !counter.containsKey(word) ? 1 : counter.get(word) + 1;
            if (count > wordCountList.get(0).count) {
                if (word.equals(wordCountList.get(0).getWord())) {
                    wordCountList.get(0).setCount(count);
                } else {
                    wordCountList.set(0, new WordCountDTO(word, count));
                }

                heapAdjust(wordCountList, i, wordCountList.size());
            }
            counter.put(word, count);

        }


        List<String> result = wordCountList.stream().map(WordCountDTO::getWord).collect(Collectors.toList());
        return result;
    }

    private void heapAdjust(List<WordCountDTO> wordCountList, int index, int length) {

        int left = index * 2 + 1;
        int right = left + 1;
        int min = index;
        if (left < length && wordCountList.get(left).count < wordCountList.get(min).count) {
            min = left;
        }
        if (right < length && wordCountList.get(right).count < wordCountList.get(min).count) {
            min = right;
        }
        if (min != index) {
            WordCountDTO temp = wordCountList.get(min);
            wordCountList.set(min, wordCountList.get(index));
            wordCountList.set(index, temp);
        }

    }

    @Data
    static class WordCountDTO {
        private String word;
        private int count;

        public WordCountDTO(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        String[] words = new String[]{"aa", "aa", "aa", "aa", "bb", "cc", "aaa", "dd", "ee", "ee", "ee", "ee", "ee", "ee"};
        new HeadpSort2().getTopKWords(words, 4);
    }
}

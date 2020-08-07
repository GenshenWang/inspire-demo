package com.wgs.algorithms.offer;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test {
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < array.length; i++) {
            int num = array[i];
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }

        boolean flag = true;
        for(int i = 0; i < array.length; i++) {
            int num = array[i];
            if(map.get(num) == 1 && flag) {
                num1[0] = num;
                flag = false;
            } else if (map.get(num) == 1 && !flag) {
                num2[0] = num;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
    }
}

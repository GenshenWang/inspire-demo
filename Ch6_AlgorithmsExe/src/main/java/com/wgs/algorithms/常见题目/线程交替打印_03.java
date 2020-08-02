package com.wgs.algorithms.常见题目;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://cloud.tencent.com/developer/article/1650130
 */
public class 线程交替打印_03 {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private volatile int num = 0;

    public void print() {

        lock.lock();
        try {

            while (num <= 10) {
                System.out.println(num++);
                condition.signalAll();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }

    }


    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {

            String tempStr = strs[i];
            int len = prefix.length() < tempStr.length() ? prefix.length() : tempStr.length();
            int commonLen = 0;
            while (commonLen < len && prefix.charAt(commonLen) == (tempStr.charAt(commonLen))) {
                commonLen++;
            }

            if (commonLen == 0) {
                return "";
            }

            prefix = prefix.substring(0, commonLen);
        }

        return prefix;

    }

    public static void main(String[] args) {
        String[]  strs = new String[]{"aca","cba"};
        new 线程交替打印_03().longestCommonPrefix(strs);

        线程交替打印_03 a = new 线程交替打印_03();
        Thread thread1 = new Thread(a::print, "thead1");
        Thread thread2 = new Thread(a::print, "thead2");

        thread1.start();
        thread2.start();

        //new 线程交替打印_02().print();
    }
}

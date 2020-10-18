package com.wgs.refactor.demo1.demo;

public class 内联Inline_08 {

    /**
     * 01 - 变量内联示例
     *
     * @param path
     * @return
     */
    public boolean doSomething(String path) {
        // Inline variable ：快捷键： alt + enter
        return doWithPath(path);
    }

    private boolean doWithPath(String path) {
        return false;
    }


    /**
     * 02 - 方法内联示例
     *
     * @param num
     * @return
     */
    public int calculate(int num) {
        return num + 2;
    }

    // 快捷键： alt + command + N
    // 点击addNum - Refactor - Inline


    /**
     * 03 - 类内联优化
     *
     *
     * @param userId
     * @return
     */
    public String queryPhone(long userId) {

        // 查询逻辑
        String phone = this.queryPhoneByUserId(userId);
        return phone.substring(0, 6) + "****";
    }

    private String queryPhoneByUserId(long userId) {
        return null;
    }


    public static void main(String[] args) {
        int a = 1;
        int ret = 0;
        int res = 0;

        ret = add(3, 5);
        res = a + ret;

        System.out.println(res);
    }

    private static int add(int x, int y) {
        return x + y;
    }



}

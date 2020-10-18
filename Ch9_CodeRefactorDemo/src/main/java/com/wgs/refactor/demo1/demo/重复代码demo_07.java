package com.wgs.refactor.demo1.demo;

public class 重复代码demo_07 {

    // 快捷键 isUserIdValid
    // alt + command + M
    public boolean addFood(Integer foodId) {
        if (isFoodIdValid(foodId)) return false;

        // ...
        return true;
    }

    private boolean isFoodIdValid(Integer foodId) {
        if (foodId == null && foodId > 0) {
            return true;
        }
        return false;
    }

    public boolean updateFood(Integer foodId) {
        if (isFoodIdValid(foodId)) return false;

        // ...
        return true;
    }



}

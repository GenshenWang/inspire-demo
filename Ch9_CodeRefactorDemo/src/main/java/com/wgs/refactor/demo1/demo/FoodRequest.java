package com.wgs.refactor.demo1.demo;

public class FoodRequest {
    private final Integer foodId;
    private final String foodName;
    private final String foodImg;
    private final String num;

    /**
     * @param foodId
     * @param foodName
     * @param foodImg
     * @param num
     */
    public FoodRequest(Integer foodId, String foodName, String foodImg, String num) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodImg = foodImg;
        this.num = num;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodImg() {
        return foodImg;
    }

    public String getNum() {
        return num;
    }
}

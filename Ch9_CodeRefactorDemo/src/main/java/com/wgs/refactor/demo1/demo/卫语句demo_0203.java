package com.wgs.refactor.demo1.demo;

import com.wgs.refactor.demo1.Food;
import com.wgs.refactor.demo1.FoodService;
import com.wgs.refactor.demo1.Price;

import java.util.List;

public class 卫语句demo_0203 {

    private FoodService foodService;

    /**
     * Demo 001 - 查看美食价格
     *
     * @param foodId
     * @return
     */
    public Integer getFoodPrice(Integer foodId) {
        if (foodId != null) {
            Food food = foodService.selectById(foodId);
            if (food != null) {
                Price price = food.getPrice();
                if (price != null) {
                    return price.getOriginPrice();
                }
            }
        }

        return null;
    }

    public Integer getFoodPrice2(Integer foodId) {
        if (foodId == null) {
            return null;
        }

        Food food = foodService.selectById(foodId);
        if (food == null) {
            return null;
        }

        Price price = food.getPrice();
        return price != null ? price.getOriginPrice() : null;
    }


    /**
     * Demo 002 - 存储美食信息
     *
     * ==》 参数超过4个的时候会影响代码可读性，使用起来不方便
     * @param foodRequest
     */
    public void saveFoodInfo(FoodRequest foodRequest) {

        // 新增或者更新美食信息
    }


    private List<Food> showNotVipFoodList() {
        return null;
    }


    private List<Food> showVipFoodList() {
        return null;
    }

    private boolean hasAlreadyBuy(Integer userId) {
        // 判断用户是否购买过该套餐
        return true;
    }

    public 卫语句demo_0203() {
        foodService = new FoodService();
    }
}

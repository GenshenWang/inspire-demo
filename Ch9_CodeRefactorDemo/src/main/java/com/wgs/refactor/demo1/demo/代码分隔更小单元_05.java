package com.wgs.refactor.demo1.demo;

import java.util.Calendar;
import java.util.Date;

/**
 * 大部分人看代码都是先看整体再看细节，所以我们要有模块化和抽象思维，
 * 善于将大块复杂逻辑提炼成类或函数，屏蔽细节，提高代码的可读性
 */
public class 代码分隔更小单元_05 {

    /**
     * 投资
     * ==> 重构前，很难一下子看懂这块时间的判断是干啥子用的
     */
    private void invest(long userId, long productId) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return;
        }

        // 省略后续逻辑
        boolean invest = this.investProduct(userId, productId);
        if (invest) {
            // ...;
        }
    }

    private boolean investProduct(long userId, long productId) {
        return false;
    }

    /**
     * 重构后代码：提炼函数逻辑更加清晰
     */
    private void invest2(long userId, long productId) {
        if (isFirstDayOfMonth(new Date())) {
            return;
        }

        // ...
        boolean invest = this.investProduct(userId, productId);
        if (invest) {
            // ...;
        }
    }

    public static boolean isFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }
        return false;
    }

}

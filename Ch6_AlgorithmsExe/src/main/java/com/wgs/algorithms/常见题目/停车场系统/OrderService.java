package com.wgs.algorithms.常见题目.停车场系统;
import com.wgs.algorithms.常见题目.停车场系统.domain.ParketOrder;
import com.wgs.algorithms.常见题目.停车场系统.domain.ParketSpace;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OrderService {

    public static final Map<String, ParketOrder> orderInfos = new HashMap<>();

    private OrderService() {}

    private static final OrderService orderService = new OrderService();

    public static OrderService getInstance() {
        return orderService;
    }

    public ParketOrder queryLatestOrder(String carNo) {
        for (Map.Entry<String, ParketOrder> entry : orderInfos.entrySet()) {
            if (entry.getValue().getCarNo().equals(carNo)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void generateOrder(String carNo, ParketSpace parketSpace) {
        ParketOrder parketOrder = new ParketOrder();
        parketOrder.setCarNo(carNo);
        parketOrder.setParketSpace(parketSpace);
        parketOrder.setOrderId(UUID.randomUUID().toString());
        parketOrder.setStartTime(System.currentTimeMillis());
        parketOrder.setTotalMoney(0);
        parketOrder.setStatus(1);

        orderInfos.put(parketOrder.getOrderId(), parketOrder);
    }


    public void finishOrder(String orderId) {
        ParketOrder parketOrder = orderInfos.get(orderId);
        parketOrder.setStatus(2);
        parketOrder.setEndTime(System.currentTimeMillis());
        long parkSeconds = (parketOrder.getEndTime() - parketOrder.getStartTime()) / 1000 * 100;
        BigDecimal totalMoney = calculateMonty(parkSeconds, parketOrder.getParketSpace().getPrice());
        parketOrder.setTotalMoney(totalMoney.setScale(2, 2).doubleValue());
    }

    private BigDecimal calculateMonty(long seconds, int price) {
        BigDecimal parkHours = BigDecimal.valueOf(seconds).divide(BigDecimal.valueOf(3600), 4, 2);
        return parkHours.multiply(BigDecimal.valueOf(price));
    }


}

package com.wgs.algorithms.常见题目.停车场系统;

import cn.hutool.core.date.DateUtil;
import com.wgs.algorithms.常见题目.停车场系统.domain.ParketOrder;
import com.wgs.algorithms.常见题目.停车场系统.domain.ParketSpace;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class ParkService {

    private static volatile Map<Integer, ParketSpace> smallParketSpaceMap = new LinkedHashMap<>();
    private static volatile Map<Integer, ParketSpace> bigParketSpaceMap = new LinkedHashMap<>();

    private ParkService() {}

    private static final ParkService parkService = new ParkService();
    public static ParkService getInstance() {
        return parkService;
    }

    static {
        for (int i = 1; i <= 50; i++) {
            ParketSpace small = new ParketSpace();
            small.setParkNo(i);
            small.setType(1);
            small.setLock(false);
            small.setPrice(5);

            smallParketSpaceMap.put(i, small);
        }

        for (int i = 51; i <= 100; i++) {
            ParketSpace big = new ParketSpace();
            big.setParkNo(i);
            big.setType(2);
            big.setLock(false);
            big.setPrice(10);

            bigParketSpaceMap.put(i, big);
        }
    }

    private ParketSpace assignParkSpace(int carType) {
        ParketSpace parketSpace = returnFreePark(carType);
        synchronized (parketSpace) {
            if (!parketSpace.isLock()) {
                parketSpace.setLock(true);
                return parketSpace;
            }
        }

        return null;
    }

    private ParketSpace returnFreePark(int carType) {
        Map<Integer, ParketSpace> parkMap = carType == 1 ? smallParketSpaceMap : bigParketSpaceMap;
        for (Map.Entry<Integer, ParketSpace> entry : parkMap.entrySet()) {
            if (!entry.getValue().isLock()) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void parkIn(String carNo, int carType) {
        // 先读

        // 分配车位
        ParketSpace parketSpace = assignParkSpace(carType);
        if (parketSpace != null) {
            throw new RuntimeException("无空闲车位");
        }

        // 生成订单
        OrderService orderService = OrderService.getInstance();
        orderService.generateOrder(carNo, parketSpace);

        System.out.println("车子" + carNo + "分配车位成功，开始计时") ;

    }

    public void parkExit(String carNo) {
        // 先读

        // 计费
        OrderService orderService = OrderService.getInstance();
        ParketOrder parketOrder = orderService.queryLatestOrder(carNo);
        orderService.finishOrder(parketOrder.getOrderId());

        // 释放停车位
        parketOrder.getParketSpace().setLock(false);

        Date startDate = DateUtil.date(parketOrder.getStartTime());
        Date endDate = DateUtil.date(parketOrder.getEndTime());
        System.out.println("车子" + carNo + "出站，"
                + " 车子类型为 " + (parketOrder.getParketSpace().getType() == 1 ? "小车" : "大车")
                + " 计费时间为【" + DateUtil.format(startDate, "yyyy-MM-dd HH:mm:ss") + " - " + DateUtil.format(endDate, "yyyy-MM-dd HH:mm:ss") + "】"
                + "花费金额为 " + parketOrder.getTotalMoney()+ "元") ;
    }

}

package com.wgs.parrot.延时消息.时间轮;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeWheelSpringBeanTest  {

    @Autowired
    private TimeWheel timeWheel;

    @RequestMapping("/pay")
    public void pay(@RequestParam("userId") long userId,
                    @RequestParam("orderId") long orderId) {
        System.out.println("用户 " + userId + " 开始准备下单了 " + orderId);

        System.out.println("用户 " + userId + " 下单成功，进入待支付状态 " + orderId + ", date= " + DateTime.now().toString());

        TimeWheel.Task task = new OrderCancelJob(orderId);
        task.setKey(10);
        timeWheel.addTask(task);

    }



    static class OrderCancelJob extends TimeWheel.Task {
        private long orderId;

        public OrderCancelJob(long orderId) {
            this.orderId = orderId;
        }

        @Override
        public void run() {
            System.out.println("订单id: " + orderId + " 支付状态" +  (orderId % 2 == 0 ? "待支付，取消" : "已支付")
                    +  " date= " + DateTime.now().toString());
        }
    }
}

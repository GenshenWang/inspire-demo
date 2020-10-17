package com.wgs.parrot.延时消息.时间轮;

import com.wgs.parrot.延时消息.时间轮.v1.RingTimeWheel;
import com.wgs.parrot.延时消息.时间轮.v1.TimeWheel;
import com.wgs.parrot.延时消息.时间轮.v2.RingTimingWheel;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeWheelSpringBeanTest  {

    @Autowired
    private TimeWheel timeWheel;
    @Autowired
    private RingTimingWheel ringTimeWheel;
    @Autowired
    private RingTimingWheel ringTimingWheel;

    /**
     * http://localhost:8080/pay?userId=100004&orderId=12345674&time=20
     *
     * @param userId
     * @param orderId
     * @param time
     */
    @RequestMapping("/pay")
    public void pay(@RequestParam("userId") long userId,
                    @RequestParam("orderId") long orderId,
                    @RequestParam("time") int time) {
        System.out.println("用户 " + userId + " 开始准备下单了 " + orderId);

        System.out.println("用户 " + userId + " 下单成功，进入待支付状态 " + orderId + ", date= " + DateTime.now().toString());

       /* TimeWheel.Task task = new OrderCancelJob(orderId);
        task.setKey(10);
        timeWheel.addTask(task);*/


       /* RingTimingWheel.Task task = new OrderCancelJob2(orderId);
        task.setKey(10);
        ringTimeWheel.addTask(task);*/

        RingTimingWheel.Task task = new OrderCancelJob3(orderId);
        task.setKey(time);
        ringTimingWheel.addTask(task);

    }

    /**
     * http://localhost:8080/stop?force=true
     *
     * @param force
     * @return
     */
    @GetMapping("/stop")
    public String stop(@RequestParam("force") String force) {
        ringTimingWheel.stop(Boolean.parseBoolean(force));
        return "Success.";
    }


    static class OrderCancelJob3 extends RingTimingWheel.Task {
        private long orderId;

        public OrderCancelJob3(long orderId) {
            this.orderId = orderId;
        }

        @Override
        public void run() {
            System.out.println("订单id: " + orderId + " 支付状态" +  (orderId % 2 == 0 ? "待支付，取消" : "已支付")
                    +  " date= " + DateTime.now().toString());
        }
    }

    static class OrderCancelJob2 extends RingTimeWheel.Task {
        private long orderId;

        public OrderCancelJob2(long orderId) {
            this.orderId = orderId;
        }

        @Override
        public void run() {
            System.out.println("订单id: " + orderId + " 支付状态" +  (orderId % 2 == 0 ? "待支付，取消" : "已支付")
                    +  " date= " + DateTime.now().toString());
        }
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

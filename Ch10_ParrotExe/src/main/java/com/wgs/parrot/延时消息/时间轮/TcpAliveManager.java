package com.wgs.parrot.延时消息.时间轮;

import com.wgs.parrot.延时消息.时间轮.v2.TcpConnectUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 有一个APP实时消息通道系统，对每个用户会维护一个APP到服务器的TCP连接，用来实时收发消息，对这个TCP连接，
 * 有这样一个需求：“如果连续30s没有请求包（例如登录，消息，keepalive包），服务端就要将这个用户的状态置为离线”。
 */
@RestController
public class TcpAliveManager {

    @Autowired
    private TcpConnectUtil tcpConnectUtil;

    /**
     * http://localhost:8080/user/visit?userId=1112
     * @param userId
     * @return
     */
    @GetMapping("/user/visit")
    public String visit(@RequestParam("userId") long userId) {
        System.out.println("用户" + userId + "访问系统， date=" + DateTime.now().toString());
        TcpConnectUtil.Task task = new UserVisit(userId);
        task.setKey(10);
        task.setUserId(userId);
        tcpConnectUtil.addTask(task);
        return "success";
    }

    static class UserVisit extends TcpConnectUtil.Task {
        private long userId;

        public UserVisit(long userId) {
            this.userId = userId;
        }

        @Override
        public void run() {
            System.out.println("用户【" + userId + "】超时，将被下线：" +  " date= " + DateTime.now().toString());
        }
    }

}

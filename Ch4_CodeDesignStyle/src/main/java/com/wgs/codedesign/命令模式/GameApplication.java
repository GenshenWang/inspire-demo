package com.wgs.codedesign.命令模式;

import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: wanggenshen
 * @date: 2020/5/1 10:56.
 * @description: XXX
 */
public class GameApplication {

    private static final int MAX_LOOP_COUNT = 10;
    private Queue<Command> queue = new LinkedList<>();

    public void mainLoop() {

        while (true) {
            // create request
            List<Request> requests = mockRequest();

            // wrap request to command
            for (Request request : requests) {
                Event event = request.getType();
                Command command = null;
                if (Event.GOT_ARROW.equals(event)) {
                    command = new GotArrowCommand(request.getData());
                } else if (Event.GOT_DIAMOND.equals(event)) {
                    command = new GotDiamondCommand(request.getData());
                } else if (Event.GOT_STAR.equals(event)) {
                    command = new GotStarCommand(request.getData());
                }

                queue.add(command);
            }

            // execute command
            int handledCount = 0;
            while (handledCount < MAX_LOOP_COUNT) {
                if (queue.isEmpty()) {
                    break;
                }
                Command command = queue.poll();
                command.execute();
                handledCount++;
            }
        }
    }

    private List<Request> mockRequest() {
        List<Request> requests = Lists.newArrayList();
        requests.add(new Request(Event.GOT_ARROW, "获取到一支箭, 攻击属性+10"));
        requests.add(new Request(Event.GOT_DIAMOND, "获取到钻石, 生命回复+20"));
        requests.add(new Request(Event.GOT_STAR, "获取到星星, 移动属性+5"));

        return requests;
    }

    public static void main(String[] args) {
        new GameApplication().mainLoop();
    }
}

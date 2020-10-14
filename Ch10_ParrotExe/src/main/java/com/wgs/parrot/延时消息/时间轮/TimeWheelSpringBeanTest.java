package com.wgs.parrot.延时消息.时间轮;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TimeWheelSpringBeanTest  {

    @Autowired
    private TimeWheel timeWheel;

    @PostConstruct
    public void triggerTimingWheelTask() {


        // 5s执行的任务
        TimeWheel.Task task = new Job2(1);
        task.setKey(5);
        timeWheel.addTask(task);
        System.out.println("Add Task1===" + DateTime.now());


        task = new Job2(2);
        task.setKey(10);
        timeWheel.addTask(task);
        System.out.println("Add Task2===" + DateTime.now());


        task = new TimeWheel.Job(3);
        task.setKey(20);
        timeWheel.addTask(task);
        System.out.println("Add Task3===" + DateTime.now());

        task = new TimeWheel.Job(4);
        task.setKey(40);
        timeWheel.addTask(task);
        System.out.println("Add Task4 time 40s===" + DateTime.now());


        timeWheel.stop(false);
    }


    static class Job extends TimeWheel.Task {
        private int num;

        public Job(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            System.out.println("Execute current job number is : " + num + ", " + DateTime.now());
        }
    }

    static class Job2 extends TimeWheel.Task {
        private int num;

        public Job2(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            int i =  10 / 0;
            System.out.println("Execute current job2 number is : " + num + ", " + DateTime.now());
        }
    }
}

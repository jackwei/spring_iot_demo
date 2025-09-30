package com.ykkj.test.delay;

import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.Timer;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class DelayTaskTest {


    public static void main(String[] args) {

    }

    @Test
    public void delayTest2() throws IOException {
        System.out.println(DateUtil.now());
        Timer timer = new Timer();
        timer.schedule(new DelayTImerTask( "==2s task"), 2000);
        timer.schedule(new DelayTImerTask( "==5s task"), 5000);
        System.in.read();
    }

    @Test
    public void delayTest(){
        DelayTask delayTask1 = new DelayTask(8, TimeUnit.SECONDS,"3s");
        DelayTask delayTask2 = new DelayTask(50, TimeUnit.SECONDS,"3s");

        DelayQueue<DelayTask> delayQueue = new DelayQueue<>();
        delayQueue.add(delayTask1);
        delayQueue.add(delayTask2);
        System.out.println(DateUtil.now());
        while (!delayQueue.isEmpty()) {
            DelayTask delayTask = delayQueue.poll();
            System.out.println("========"+DateUtil.now());
            if(delayTask != null){
                System.out.println(delayTask.getName()+"===="+DateUtil.now());
            }
        }

    }
}

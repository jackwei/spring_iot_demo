package com.ykkj.test.delay;

import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class DelayTImerTask extends TimerTask {
    private String name;

    public DelayTImerTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + this.name + " 任务执行了");
    }



}

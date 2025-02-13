package com.ykkj.test.demo.design.observer;

import com.google.common.eventbus.Subscribe;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName EventListener.java
 * @Description TODO
 * @createTime 2022年10月27日
 */
public class EventListener {


    @Subscribe
    public void handle(NotifyEvent notifyEvent) {
        System.out.println("发送IM消息" + notifyEvent.getImNo());
        System.out.println("发送短信消息" + notifyEvent.getMobileNo());
        System.out.println("发送Email消息" + notifyEvent.getEmailNo());
    }

}

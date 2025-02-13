package com.ykkj.test.demo.design.observer;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName EventBusDemoTest.java
 * @Description TODO
 * @createTime 2022年10月27日
 */
public class EventBusDemoTest {

    public static void main(String[] args) {


        EventListener eventListener = new EventListener();
        EventBusCenter.register(eventListener);
        EventListener2 eventListener2 = new EventListener2();
        EventBusCenter.register(eventListener2);
        EventBusCenter.post(new NotifyEvent("111","222","33"));

    }
}

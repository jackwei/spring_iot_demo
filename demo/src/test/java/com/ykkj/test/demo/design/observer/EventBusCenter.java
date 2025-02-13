package com.ykkj.test.demo.design.observer;

import com.google.common.eventbus.EventBus;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName EventBusCenter.java
 * @Description TODO
 * @createTime 2022年10月27日
 */
public class EventBusCenter {


    public static EventBus eventBus = new EventBus();

    public static EventBus getInstance(){
        return eventBus;
    }

    public static  void register (Object object){
        eventBus.register(object);
    }

    public static void post(Object object){
        eventBus.post(object);
    }



}

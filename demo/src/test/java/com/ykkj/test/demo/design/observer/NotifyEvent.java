package com.ykkj.test.demo.design.observer;

import lombok.Data;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName NotifyEvent.java
 * @Description TODO
 * @createTime 2022年10月27日
 */
@Data
public class NotifyEvent {

    private String mobileNo;

    private String emailNo;

    private String imNo;

    public NotifyEvent(String mobileNo, String emailNo, String imNo) {
        this.mobileNo = mobileNo;
        this.emailNo = emailNo;
        this.imNo = imNo;
    }
}

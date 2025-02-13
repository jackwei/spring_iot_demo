package com.ykkj.model;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName ServiceRuntimeException.java
 * @Description TODO
 * @createTime 2022年09月08日
 */

/**
 * 自定义运行类异常抛出
 */
public class ServiceRuntimeException extends RuntimeException{
    public ServiceRuntimeException() {
    }
    public ServiceRuntimeException(String msg) {
        super(msg);
    }

}


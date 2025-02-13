package com.ykkj.enums;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName LimitType.java
 * @Description TODO
 * @createTime 2022年09月08日
 */

public enum LimitType {

    /**
     * 默认策略全局限流
     */
    DEFAULT,

    /**
     * 根据请求者IP进行限流
     */
    IP
}

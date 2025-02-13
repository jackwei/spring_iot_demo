package com.ykkj.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 通用开关注解
 * </p>
 *
 * @author 程序员济癫
 * @since 2023-10-16 17:38
 */
@Target({ElementType.METHOD})  // 作用在方法上
@Retention(RetentionPolicy.RUNTIME)  // 运行时起作用
public @interface ServiceSwitch {

    /**
     * 业务开关的key（不同key代表不同功效的开关）
     */
    String switchKey();

    // 开关，0:关(拒绝服务并给出提示)，1:开(放行)
    String switchVal() default "0";

    // 提示信息，默认值可在使用注解时自行定义。
    String message() default "当前请求人数过多，请稍后重试。";
}


package com.ykkj.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ykkj.enums.TransferStrategyEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * JacksonAnnotationsInside：Jackson 库中的一个元注解，主要用于创建自定义组合注解。它的作用是指示 Jackson 在处理被该注解标记的自定义注解时，应该进一步解析自定义注解内部的其他注解
 *
 * 加上该注解后，在使用Transfer注解时，JsonSerialize注解也会生效，否则JsonSerialize注解不生效，如果不想用这个注解，也可以直接在字段上使用JsonSerialize注解
 */
@JacksonAnnotationsInside
/**
 * JsonSerialize：自定义对象的序列化行为
 * 指定自定义序列化逻辑实现类
 */
@JsonSerialize(using = TransferJsonSerialize.class)
public @interface Transfer {

    /**
     * 转换类型
     * 该参数决定具体使用的转换逻辑
     */
    TransferStrategyEnum transferStrategy();

    /**
     * 是否覆盖源字段
     * 值为true时，转换值将覆盖源字段值
     * 值为false时，源字段值不变，转换值将设置到目标转换字段中（目标转换字段不能为空，否则转换失败）
     */
    boolean overrideSelf() default true;

    /**
     * 目标转换字段名
     */
    String targetFieldName() default "";

}

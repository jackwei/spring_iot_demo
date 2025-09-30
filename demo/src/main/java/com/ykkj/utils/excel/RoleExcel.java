package com.ykkj.utils.excel;

import java.lang.annotation.*;

/**
 * 扩展Excel注解，支持按角色控制导出字段
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RoleExcel {
    // 字段显示名称（同若依@Excel的name）
    String name();

    // 列宽（默认20）
    int width() default 20;

    // 日期格式（如"yyyy-MM-dd HH:mm:ss"）
    String dateFormat() default "";

    // 允许导出该字段的角色标识列表（如"admin","audit_role"）
    String[] roles() default {};

    // 是否允许所有角色导出（默认false）
    boolean allowAll() default false;

    // 权限标识（支持按权限控制，如"jihe:export:vehplate"）
    String[] permissions() default {};
}

package com.ykkj.utils;

/**
 * 错误枚举类
 */
public enum ExceptionEnum {
    UNKNOW_ERROR(-1,"未知错误"),
    ERROR_404(404,"没有资源"),
    ERROR_403(403,"没有权限"),
    LOGIN_ERROR(1000,"登陆失败！请检查用户名密码是否正确"),
    LOGIN_SUCCESS(1001,"登陆成功！"),
    LOGIN_SESSIONINVALID(1002,"用户登录超时"),
    LOGIN_KICKOUT(1003,"用户在其他地方登录或被踢出"),
    LOGIN_NO(1004,"用户没有登录！请登录"),
    LOGIN_LOCK(1005,"账户被锁定，请联系管理员!"),
    LOGIN_PASS_EXPIRED(1006,"密码过期，请联系管理员!"),
    LOGIN_USERNAME_EXPIRED(1007,"账号过期，请联系管理员!"),
    LOGIN_UNENABLE(1008,"账号禁用，请联系管理员!"),
    TOKEN_EXPIRED(2001,"登录超时"),
    TOKEN_ERROR(2002,"TOKEN错误请重新获取"),
    TOKEN_SIGNA_ERROR(2002,"TOKEN签名错误请重新获取"),

    DATA_PERMISSION_UC_ERROR(3000,"您没有此单位数据权限"),
    DATA_PERMISSION_NO_UC(3001,"您还没有加入任何所属公司，请加入公司"),
    DATA_PERMISSION_NOT_ADMIN(3002,"不是系统管理员，没有系统管理权限"),
    SENSITIVE_WORD_ERROR(4000,"不能输入敏感词")
    ;

    private Integer code;

    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

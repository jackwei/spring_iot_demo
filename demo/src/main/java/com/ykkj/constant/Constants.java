package com.ykkj.constant;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName Constants.java
 * @Description TODO
 * @createTime 2022年09月08日
 */

public class Constants {

    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";


    /**
     * 限流 redis key
     */
    public static final String RATE_LIMIT_KEY = "rate_limit:";

    // 配置相关的常量
    public static class ConfigCode {

        // 挂号支付开关(0:关，1:开)
        public static final String REG_PAY_SWITCH = "reg_pay_switch";
        // 门诊支付开关(0:关，1:开)
        public static final String CLINIC_PAY_SWITCH = "clinic_pay_switch";

        // 其他业务相关的配置常量
        // ....
    }
}

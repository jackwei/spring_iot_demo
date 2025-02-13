package com.ykkj.aspect;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Aspect
@Component
public class TraceIdAspect {

    /** 链路追踪id */
    public final static String TRACE_ID = "TRACE_ID";
    /** 用户 */
    public final static String USER = "USER";

    /**
     * 链路id切点定义
     */
    @Pointcut("execution(* com.ykkj.controller.*.*(..))")
    public void TraceIdCut() {

    }

    /**
     * 链路id添加
     */
    @Before("TraceIdCut()")
    public void cutProcessBefore() {
        MDC.put(TRACE_ID, getUUID());
        //以下代码为记录用户信息，方便更直观的识别日志操作人信息。若不需要可以将下面部分删除
        String nickname = RandomUtil.randomString(5);// TokenUtil.getNicknameByToken();
        if (null == nickname){
            nickname = "游客访问";
        }
        MDC.put(USER, nickname);
    }

    public static String getUUID() {
        //生产uuid并去掉uuid的短横线
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 链路id清除
     */
    @After("TraceIdCut()")
    public void cutProcessAfter() {
        MDC.clear();
    }
}
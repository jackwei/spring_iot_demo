package com.ykkj.aspect;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName RateLimiterAspect.java
 * @Description TODO
 * @createTime 2022年09月08日
 */

import com.ykkj.annotation.RateLimiter;
import com.ykkj.enums.LimitType;
import com.ykkj.model.ServiceRuntimeException;
import com.ykkj.utils.RedisUtils;
import com.ykkj.utils.ServletUtils;
import com.ykkj.utils.ip.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Jae
 * @date 2022/8/23 10:01
 * @description: 限流切面
 */
@Aspect
@Component
public class RateLimiterAspect {

    private final static Logger log = LoggerFactory.getLogger(RateLimiterAspect.class);

    @Autowired
    private RedisUtils redisUtils;

    @Before("@annotation(rateLimiter)")
    public void doBefore(JoinPoint point, RateLimiter rateLimiter) throws Throwable
    {
        int time = rateLimiter.time();
        int count = rateLimiter.count();
        long total = 1L;

        String combineKey = getCombineKey(rateLimiter, point);
        System.out.println(combineKey);
        try
        {
            if(redisUtils.hasKey(combineKey)){
                total = redisUtils.incr(combineKey,1);  //请求进来，对应的key加1
                if(total > count)
                    throw new ServiceRuntimeException(rateLimiter.limitMsg());
            }else{
                redisUtils.set(combineKey,1,time);  //初始化key
            }
        }
        catch (ServiceRuntimeException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new ServiceRuntimeException("网络繁忙，请稍候再试");
        }
    }

    /**
     * 获取限流key
     * @param rateLimiter
     * @param point
     * @return
     */
    public String getCombineKey(RateLimiter rateLimiter, JoinPoint point)
    {
        StringBuffer stringBuffer = new StringBuffer(rateLimiter.key());
        if (rateLimiter.limitType() == LimitType.IP)
        {
            stringBuffer.append(IpUtils.getIpAddr(ServletUtils.getRequest())).append("-");
        }
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        stringBuffer.append(targetClass.getName()).append("-").append(method.getName());
        return stringBuffer.toString();
    }



}

package com.ykkj.aspect;


import com.ykkj.annotation.ServiceSwitch;
import com.ykkj.constant.Constants;
import com.ykkj.utils.Result;
import com.ykkj.utils.ResultUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * <p>
 * 开关实现的切面类
 * </p>
 *
 * @author 程序员济癫
 * @since 2023-10-16 17:56
 */
@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class ServiceSwitchAOP {

    private final StringRedisTemplate redisTemplate;

    /**
     * 定义切点，使用了@ServiceSwitch注解的类或方法都拦截
     */
    @Pointcut("@annotation(com.ykkj.annotation.ServiceSwitch)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Exception {

        // 获取被代理的方法的参数
        Object[] args = point.getArgs();
        // 获取被代理的对象
        Object target = point.getTarget();
        // 获取通知签名
        MethodSignature signature = (MethodSignature) point.getSignature();

        try {

            // 获取被代理的方法
            Method method = target.getClass().getMethod(signature.getName(), signature.getParameterTypes());
            // 获取方法上的注解
            ServiceSwitch annotation = method.getAnnotation(ServiceSwitch.class);

            // 核心业务逻辑
            if (annotation != null) {

                String switchKey = annotation.switchKey();
                String switchVal = annotation.switchVal();
                String message = annotation.message();

            /*
              获取配置项说明
              这里有两种方式：1、配置加在Redis，查询时从Redis获取；
                          2、配置加在数据库，查询时从表获取。(MySQL单表查询其实很快，配置表其实也没多少数据)
              我在工作中的做法：直接放到数据库，但是获取配置项的方法用SpringCache缓存，
                           然后在后台管理中操作配置项，变更时清理缓存即可。
                           我这么做就是结合了上面两种各自的优点，因为项目中配置一般都是用后台管理来操作的，
                           查表当然更舒适，同时加上缓存提高查询性能。
             */

                // 下面这块查询配置项，大家可以自行接入并修改。
                // 数据库这么查询：String configVal = systemConfigService.getConfigByKey(switchKey);
                // 这里我直接从redis中取，使用中大家可以按照意愿自行修改。
                String configVal = redisTemplate.opsForValue().get(Constants.ConfigCode.REG_PAY_SWITCH);
                if (switchVal.equals(configVal)) {
                    // 开关打开，则返回提示。
                    return ResultUtil.error(message);
                }
            }

            // 放行
            return point.proceed(args);

        } catch (Throwable e) {
            throw new Exception(e.getMessage(), e);
        }
    }
}


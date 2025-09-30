package com.ykkj.task;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class MyBusinessThreadPoolConfig {


    @Bean("businessExecutor")
    public ThreadPoolExecutor businessExecutor() {
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maxPoolSize = corePoolSize * 2;

        return new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000)); // 可动态调整的队列
//                new NamedThreadFactory("business",false),
//                new BusinessRejectedExecutionHandler()) {
//
//            @Override
//            protected void beforeExecute(Thread t, Runnable r) {
//                // 记录任务开始时间
//                MDC.put("traceId", UUID.randomUUID().toString());
//            }
//
//            @Override
//            protected void afterExecute(Runnable r, Throwable t) {
//                // 清理上下文，记录监控指标
//                MDC.clear();
//                //Metrics.timer("task.duration").record(...);
//            }
        };
    }


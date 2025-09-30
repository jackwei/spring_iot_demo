package com.ykkj.task;

import org.springframework.context.annotation.Bean;

import java.util.concurrent.*;

public class NewThreadPoolConfig {

    // 订单处理：高优先级，快速响应
    @Bean("orderExecutor")
    public ThreadPoolExecutor orderExecutor() {
        return new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(200), // 有界队列，快速响应
                new NamedThreadFactory("order"));
        //new OrderRejectedExecutionHandler());
    }

    // 报表导出：低优先级，可容忍延迟
    @Bean("reportExecutor")
    public ThreadPoolExecutor reportExecutor() {
        return new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1000), // 无界队列，可堆积
                new NamedThreadFactory("report"),
                new ThreadPoolExecutor.DiscardPolicy());
    }

    // 消息发送：中等优先级
    @Bean("messageExecutor")
    public ThreadPoolExecutor messageExecutor() {
        return new ThreadPoolExecutor(8, 16, 30, TimeUnit.SECONDS,
                new SynchronousQueue<>(), // 不缓存，直接传递
                new NamedThreadFactory("message"),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}

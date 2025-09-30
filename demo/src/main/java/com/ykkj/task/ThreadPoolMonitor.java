package com.ykkj.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class ThreadPoolMonitor {

    @Scheduled(fixedRate = 5000) // 每5秒监控一次
    public void monitor() {
        for (ThreadPoolExecutor executor : getAllExecutors()) {
            int corePoolSize = executor.getCorePoolSize();
            int activeCount = executor.getActiveCount();
            long completedTaskCount = executor.getCompletedTaskCount();
            int queueSize = executor.getQueue().size();

            // 告警条件
            if (queueSize > 1000) {
                //sendAlert("线程池" + executor + "队列积压：" + queueSize);
            }

            if ((double) activeCount / corePoolSize > 0.8) {
                //sendAlert("线程池" + executor + "使用率超过80%");
            }
        }
    }

    public List<ThreadPoolExecutor> getAllExecutors() {
        return new ArrayList<>();
    }


}

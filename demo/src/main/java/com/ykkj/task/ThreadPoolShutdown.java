package com.ykkj.task;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class ThreadPoolShutdown {

    ExecutorService executor = Executors.newCachedThreadPool();

    @PreDestroy
    public void gracefulShutdown() {

        // 1. 停止接收新任务
        executor.shutdown();

        try {
            // 2. 等待现有任务完成，最多等30分钟
            if (!executor.awaitTermination(30, TimeUnit.MINUTES)) {
                // 3. 强制关闭
                List<Runnable> droppedTasks = executor.shutdownNow();
                log.warn("线程池强制关闭，丢弃任务数：" + droppedTasks.size());

                // 4. 再次等待
                if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                    log.error("线程池未能正常关闭");
                }
            }
        } catch (InterruptedException e) {
            // 5. 重新尝试强制关闭
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}

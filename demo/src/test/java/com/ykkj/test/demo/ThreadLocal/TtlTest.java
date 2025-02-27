package com.ykkj.test.demo.ThreadLocal;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.alibaba.ttl.threadpool.TtlExecutors;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName TtlTest.java
 * @Description TODO
 * @createTime 2023年02月10日
 */
public class TtlTest {
    static ExecutorService executorService = Executors.newFixedThreadPool(1);
    static ExecutorService asyncExecutorService = Executors.newFixedThreadPool(1);
    static ExecutorService ttlExecutorService;
    static CountDownLatch asyncCtl = new CountDownLatch(1);

    static {
        new Thread(() -> {
            asyncExecutorService.execute(() -> {
                System.out.println("初次创建异步线程池");
            });
            ttlExecutorService = TtlExecutors.getTtlExecutorService(asyncExecutorService);
            ttlExecutorService.execute(() -> {
                System.out.println("-------------");
            });
            asyncCtl.countDown();
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        try {
            asyncCtl.await();
        } catch (InterruptedException e) {
        }

        Command.setName("main-name");
        Command.setKey("main-key");
        Command.setCodec("main-codec");

        System.out.println("main线程获取变量值");
        System.out.println(Command.getName() + "," + Command.getKey() + "," + Command.getCodec());

        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread thread1 = new Thread(() -> {
            System.out.println("新线程获取变量值");
            System.out.println(Command.getName() + "," + Command.getKey() + "," + Command.getCodec());
            countDownLatch.countDown();
        });
        thread1.start();
        countDownLatch.await();

        CountDownLatch countDownLatch2 = new CountDownLatch(1);
        executorService.execute(() -> {
            System.out.println("同步创建-线程池获取变量值");
            System.out.println(Command.getName() + "," + Command.getKey() + "," + Command.getCodec());
            countDownLatch2.countDown();
        });
        countDownLatch2.await();

        CountDownLatch countDownLatch3 = new CountDownLatch(1);
        asyncExecutorService.execute(() -> {
            System.out.println("异步创建-线程池获取变量值");
            System.out.println(Command.getName() + "," + Command.getKey() + "," + Command.getCodec());
            countDownLatch3.countDown();
        });
        countDownLatch3.await();

        CountDownLatch countDownLatch4 = new CountDownLatch(1);
        ttlExecutorService.execute(() -> {
            System.out.println("ttl-线程池获取变量值");
            System.out.println(Command.getName() + "," + Command.getKey() + "," + Command.getCodec());
            countDownLatch4.countDown();
        });
        countDownLatch4.await();


    }
}


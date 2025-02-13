package com.ykkj.test.demo.ThreadLocal;

import cn.hutool.core.thread.ExecutorBuilder;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.sql.SQLOutput;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName ExecutorServiceTest.java
 * @Description TODO
 * @createTime 2023年02月21日
 */
public class ExecutorServiceTest {

    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread12-call-runner-%d").build();

    ExecutorService executorService = Executors.newFixedThreadPool(6,namedThreadFactory);
    private static int number;


    public void test1(int number){
        executorService.submit(() -> {
            System.out.println(Thread.currentThread()+"==");
            System.out.println(number);
        });
    }

    public synchronized void test2(){
        executorService.submit(() -> {
            System.out.println(Thread.currentThread()+"==");
            System.out.println(number);
        });
    }

    public void test4(){
        executorService.submit(new MyRunnable(number));
    }

    public static void main(String[] args) throws InterruptedException {


        ExecutorServiceTest executorServiceTest = new ExecutorServiceTest();
        //executorServiceTest.test4();

        number = 6;
        executorServiceTest.test4();
        number = 7;
        executorServiceTest.test4();
        number = 8;
        executorServiceTest.test4();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("ok");
        System.exit(0);
    }

    public void test3() throws InterruptedException {
        ExecutorServiceTest executorServiceTest = new ExecutorServiceTest();
        number = 6;
        executorServiceTest.test1(61);
        number = 7;
        executorServiceTest.test1(71);
        number = 8;
        executorServiceTest.test1(81);
        TimeUnit.SECONDS.sleep(2);
        System.out.println("ok");
        System.exit(0);
    }

    class MyRunnable implements Runnable{

        int number ;
        MyRunnable(int number){
            this.number = number;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread()+"==");
            System.out.println(number);
        }
    }

}

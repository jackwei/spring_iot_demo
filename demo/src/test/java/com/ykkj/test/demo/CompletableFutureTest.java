package com.ykkj.test.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName CompletableFutureTest.java
 * @Description TODO
 * @createTime 2022年09月19日
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->factorial(11));

        while (!completableFuture.isDone()){
            System.out.println("not yet...");
        }
        int result = completableFuture.get();

        System.out.println(result);
    }

    public static int factorial(Integer number){
        return  number;
    }
}

package com.ykkj.test;

public class VolatileExample {
    private volatile boolean running = true;

    public void test() {
        new Thread(() -> {
            while (running) {
                // 执行某些操作
                System.out.println("线程正在运行...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        running = false; // 修改running的值，线程会立即停止
    }

    public static void main(String[] args) {
        new VolatileExample().test();
    }

}

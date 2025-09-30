package com.ykkj;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName InitRunner.java
 * @Description TODO
 * @createTime 2023年03月22日
 */
@Component
public class InitRunner implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i < 20; i++) {

            System.out.println("start==== :"+i);
            TimeUnit.SECONDS.sleep(5);

        }

    }
}

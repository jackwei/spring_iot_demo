package com.ykkj.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName RemoteServiceTest.java
 * @Description TODO
 * @createTime 2023年01月13日
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RemoteServiceTest {

    @Resource
    RemoteService remoteService;


    @Test
    public void get(){
        String msg = remoteService.taskHandle();
        System.out.println(msg);
    }

}

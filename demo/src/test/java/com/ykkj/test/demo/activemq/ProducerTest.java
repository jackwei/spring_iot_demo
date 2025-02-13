package com.ykkj.test.demo.activemq;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProducerTest.class)
public class ProducerTest {


    String url = "http://ykkj.yunkukeji.com:7345/uwb-data";

    @Test
    public void testSendWan(){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type",7001);

        JSONObject contentJson = JSONUtil.createObj();
        contentJson.put("devEui","009569000001628A");
        contentJson.put("data","FD 01 05 11 11 00 01 46 04 00 35 A5 EC 00 00 49");
        contentJson.put("timestamp",System.currentTimeMillis());
        contentJson.put("fPort",1);
        jsonObject.put("content",contentJson);
        jsonObject.put("projectId","6196044f352df3000156fa11");
        jsonObject.put("serverTime", System.currentTimeMillis());

        HttpUtil.post(url,jsonObject.toString());

        log.info("ok");
    }

    @Test
    public void testSyncSend() throws Exception {
        for (int i = 0; i < 1; i++) {
            log.info(""+i);
            int id = i+100;//(int) (System.currentTimeMillis() / 1000);
            //producer.syncSend();
            //producer.send7004();
            //logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
        }

        // 阻塞等待，保证消费
        //new CountDownLatch(1).await();
    }

}

package com.ykkj.test.demo.activemq.producer;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ykkj.test.demo.activemq.ActiveMQConfig;

import lombok.extern.slf4j.Slf4j;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @author lw
 * @version 1.0.0
 * @ClassName ProducerDemo.java
 * @Description TODO
 * @createTime 2021年11月18日
 */
@Slf4j
@Component
public class ProducerDemo {

    @Resource(name = ActiveMQConfig.BROADCAST_JMS_TEMPLATE_BEAN_NAME)
    private JmsMessagingTemplate jmsTemplate;

    public void syncSend() throws Exception {
        // 创建 Demo03Message 消息

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type",7001);

        JSONObject contentJson = JSONUtil.createObj();
        contentJson.put("devEui","A70B768792B28280");
        contentJson.put("data","FD 01 05 11 11 00 01 46 04 00 35 A5 EC 00 00 49");
        contentJson.put("timestamp",System.currentTimeMillis());
        contentJson.put("fPort",1);
        jsonObject.put("content",contentJson);
        jsonObject.put("projectId","6195b124352df3000155208a");
        jsonObject.put("serverTime", System.currentTimeMillis());

        log.info(jsonObject.toString());
        //PushDataUtils.checkAndDecrypt(jsonObject.toString(),System.currentTimeMillis())
        // 同步发送消息
        jmsTemplate.convertAndSend("YKKJ",jsonObject.toString());
    }

    /**
     * ：网关上线通知
     * "type": 7004,
     * "content": {
     * "gatewayEui": "FFFE6CECEBD7E077"
     * },
     * "projectId": "5b8400ec24583b6eb04c628a",
     * "serverTime": 1541486795717
     */
    public void send7004(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type",7004);
        JSONObject contentJson = JSONUtil.createObj();
        contentJson.put("gatewayEui","A99DED73121BE1F1");
        jsonObject.put("content",contentJson);
        jsonObject.put("projectId","6195b124352df3000155208a");
        jsonObject.put("serverTime", System.currentTimeMillis());

        log.info(jsonObject.toString());
        //PushDataUtils.checkAndDecrypt(jsonObject.toString(),System.currentTimeMillis())
        // 同步发送消息
        jmsTemplate.convertAndSend("YKKJ",jsonObject.toString());
    }


    public static void main(String[] args) {
        ProducerDemo producerDemo = new ProducerDemo();
    }



}

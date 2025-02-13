package com.ykkj.test.demo.activemq.consumer;

import com.ykkj.test.demo.activemq.ActiveMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName ConsumerDemo.java
 * @Description TODO
 * @createTime 2021年11月18日
 */
@Slf4j
@Component
public class ConsumerDemo {

    @JmsListener(destination = "YKKJ",containerFactory = ActiveMQConfig.BROADCAST_JMS_LISTENER_CONTAINER_FACTORY_BEAN_NAME)
    public void onMessage(Message message) throws Exception {
        if(message instanceof ActiveMQBytesMessage){
            ActiveMQBytesMessage bytesMessage = (ActiveMQBytesMessage) message;
            byte[] bt = new byte[(int) bytesMessage.getBodyLength()];

        }else if(message instanceof ActiveMQTextMessage){

            log.info("topic message 接受到：" + ((TextMessage) message).getText());
        }else{
            log.info("topic message 接受到：" + message);
        }
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}

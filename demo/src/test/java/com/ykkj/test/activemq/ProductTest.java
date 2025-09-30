package com.ykkj.test.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ProductTest {

    private static final int SENDNUM = 1000;

    public static void main(String[] args) throws Exception {
        //1.创建connectionfacoty，参数是activemq的服务地址，前缀tcp代表是tcp连接
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("xt0001","Cmyy@JC!#335","tcp://lcty-amqp.lctytech.com:36116");
        //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("xt0001","Cmyy@JC!#335","tcp://123.56.31.165:36116");
        //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("xt0001","Cmyy@JC!#335","tcp://118.190.144.193:61616");

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("xt0001","Cmyy@JC!#335","tcp://ykkj.yunkukeji.com:7906");


        //2.使用ConnectionFactory创建connnect,并启动connnect
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //3.使用Connection创建session,第一个参数是是否使用事务，第二个参数是确认机制
        Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地(这里以PTP为例，所以目的地是一个Queue)，参数是Queue的名字
        Destination destination = session.createTopic("YXKJ.UP.*");
        //5.创建生产者，第一个参数是目的地，此时创建的生产者要与目的地进行绑定。
        MessageProducer producer = session.createProducer(destination);
        //6.使用session创建消息，这里使用TEXT类型的消息
        //TextMessage textMessage = session.createTextMessage("hello world!");
        //7.生产者发送消息
        //producer.send(textMessage);

        sendMessage(session, producer);

        //8.提交事务
        session.commit();
        //9.关闭资源
        session.close();
        connection.close();
    }

    public static void sendMessage(Session session,MessageProducer messageProducer) throws Exception{
        for (int i = 0; i < SENDNUM; i++) {
            TextMessage message = session.createTextMessage("ActiveMq 发送消息"+i);
            messageProducer.send(message);
        }
    }


}

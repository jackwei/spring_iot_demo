package com.ykkj.test.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ConsumerTest {

    public static void main(String[] args) throws JMSException {
        //1.创建connectionfacoty

        //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("xt0001","Cmyy@JC!#335","tcp://123.56.31.165:36116");
        //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("xt0001","Cmyy@JC!#335","tcp://118.190.144.193:61616");
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("xt0001","Cmyy@JC!#335","tcp://lcty-amqp.lctytech.com:36116");

        //2.创建connnect,并启动connnect
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //3.创建session,第一个参数是是否使用事务，第二个参数是确认机制
        Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地【消费者与生产者的目的地相同才能进行消息传递】
        //Destination destination = session.createQueue("tempqueue");
        Destination destination = session.createTopic("YXKJ.UP.*");

        //5.创建消费者，第一个参数是目的地，此时创建的消费者要与目的地进行绑定。
        MessageConsumer consumer = session.createConsumer(destination);

        while(true){
            //6.使用消费者接受消息消息
            TextMessage message = (TextMessage) consumer.receive();
            if(message!=null){
                //System.out.println(message.getText());
                System.out.println(message.getText());
            }else {
                break;
            }
        }

        System.out.println("ok");

        //8.提交事务
        //session.commit();
        //9.关闭资源
        //session.close();
        //connection.close();
    }
}

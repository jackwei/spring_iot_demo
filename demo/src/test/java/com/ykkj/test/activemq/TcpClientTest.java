package com.ykkj.test.activemq;

import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.socket.SocketUtil;

import java.io.DataOutputStream;
import java.net.Socket;

public class TcpClientTest {

    private static final int CPU_COUNT = RuntimeUtil.getProcessorCount();

    public static void main(String[] args)  {

        System.out.println(CPU_COUNT);

        boolean isConnected = true;
        try {
            Socket s = SocketUtil.connect("lcty-amqp.lctytech.com",3611,2000);
        }catch (Exception e){
            isConnected = false;
            e.printStackTrace();
        }

        System.out.println(isConnected);


    }

    public void socketClientTest() throws Exception {
        Socket s = new Socket("118.190.144.193",61626);

        DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());

        dataOutputStream.writeUTF("haha.test");

        dataOutputStream.flush();

        dataOutputStream.close();

        s.close();
    }

}

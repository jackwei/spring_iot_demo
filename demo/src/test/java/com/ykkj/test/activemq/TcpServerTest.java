package com.ykkj.test.activemq;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerTest {

    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(6666);

        while(true){

            Socket s = ss.accept();

            DataInputStream stream = new DataInputStream(s.getInputStream());

            System.out.println(stream.readUTF());

            stream.close();

            s.close();

        }

    }

}

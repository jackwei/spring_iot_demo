package com.ykkj.demo.nplink;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Udp1699 {

	public static void listen() throws IOException
	{
		 DatagramSocket ds = new DatagramSocket(1699);  //监听10000端口  
		  
	        //定义数据包，用于存储数据  
		 while(true)
		 {
			 byte[] buf = new byte[1024];  
		        DatagramPacket dp = new DatagramPacket(buf,buf.length);  
		  
		        ds.receive(dp);   
		  
		       
		        System.out.println(ByteUtil.bytes2HexString(dp.getData()));  
		        System.out.println(new String(dp.getData()));  
		        
		        InetAddress address=InetAddress.getByName("iot.nplink.com");
		        DatagramSocket	datagramSocket = new DatagramSocket();
				
	            byte[] buffer=dp.getData();
	            DatagramPacket packet=new DatagramPacket(buffer, buffer.length, address, 1699);
	            datagramSocket.send(packet);
	            //接收数据
	            DatagramPacket inputPacket = new DatagramPacket(new byte[512], 512);  
	           // datagramSocket.receive(inputPacket);    //第一次应接收到类似下面的登录成功消息
	           // System.out.println("RE:"+ByteUtil.bytes2HexString(inputPacket.getData()));  
	           // DatagramPacket send = new DatagramPacket(inputPacket.getData(),inputPacket.getLength(),dp.getAddress(),dp.getPort());  
	           // ds.send(send);
	            
		 }
	       
	        
	}
	public static void main(String[] args) throws IOException
	{
		
		listen();
	}
}

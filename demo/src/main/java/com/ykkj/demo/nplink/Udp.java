package com.ykkj.demo.nplink;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Base64;


public class Udp {

	public static void listen() throws IOException
	{
		 DatagramSocket ds = new DatagramSocket(1700);  //监听10000端口  
		  
	        //定义数据包，用于存储数据  
		 while(true)
		 {
			 byte[] buf = new byte[1024];  
		        DatagramPacket dp = new DatagramPacket(buf,buf.length);  
		  
		        ds.receive(dp);   
		  
		       
		        //System.out.println(ByteUtil.bytes2HexString(dp.getData()));  
		        String msg=new String(dp.getData());
		        System.out.println(msg);  
		        if(msg.indexOf("data\":\"")>=0)
		        {
		        	try
		        	{
		        	msg=msg.substring(msg.indexOf("data\":\""),msg.length());
		        	msg=msg.substring(7,msg.lastIndexOf("\""));
		        	byte[] payloadPlain=Base64.getDecoder().decode(msg);
		    		System.out.println("HEX:"+ ByteUtil.bytes2HexString(payloadPlain));
		        	}
		        	catch(Exception e)
		        	{
		        	
		        	}
		        }
		        
		        System.out.println(msg);  
		        
		        InetAddress address=InetAddress.getByName("iot.nplink.com");
		        DatagramSocket	datagramSocket = new DatagramSocket();
				
	            byte[] buffer=dp.getData();
	            DatagramPacket packet=new DatagramPacket(buffer, buffer.length, address, 1700);
	            datagramSocket.send(packet);
	            //接收数据
	            DatagramPacket inputPacket = new DatagramPacket(new byte[512], 512);  
	            datagramSocket.receive(inputPacket);    //第一次应接收到类似下面的登录成功消息
	            System.out.println("RE:"+ByteUtil.bytes2HexString(inputPacket.getData()));  
	            DatagramPacket send = new DatagramPacket(inputPacket.getData(),inputPacket.getLength(),dp.getAddress(),dp.getPort());  
	            ds.send(send);
	            
		 }
	       
	        
	}
	public static void main(String[] args) throws IOException
	{
		byte[] payloadPlain=Base64.getDecoder().decode("QFg+gk4AAQECDhwz5trmTEJmSNWPDkpbwvGBrOmk8AQ9ZwoWrs3Nn047iLX7qyXuh/AARWh08VQcJI/Q1OHSdkr1yl9V8nMv7Yr+hg==");
		System.out.println(ByteUtil.bytes2HexString(payloadPlain));
		System.out.println(new String(payloadPlain));
		//listen();
		/**
		"data":"QFg+gk4AAQECDhwz5trmTEJmSNWPDkpbwvGBrOmk8AQ9ZwoWrs3Nn047iLX7qyXuh/AARWh08VQcJI/Q1OHSdkr1yl9V8nMv7Yr+hg=="}
	,"data":"QMae2i0AzwECN0U+c5oRt+A4eODpEjzEY5tR7slKSLhSponPSbTCKN0KkoI+Y87nifiq/soWuW652W2xTu62GjFeSsmIWx+0FJNErw=="}
"data":"QBcRLCIASgACtalc6Kp65gZvkEpdltHePOosKzcX7W6fJ1VU+yNKKLd5uszrGLHuBUZpy4DtwHDFe70906I="}]}
*/
	}
}

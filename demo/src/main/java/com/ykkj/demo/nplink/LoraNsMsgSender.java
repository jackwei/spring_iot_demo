package com.ykkj.demo.nplink;
/*
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Base64;

import com.ykkj.compute.util.ConfigCache;
import com.ykkj.compute.util.Loger;
import com.ykkj.iot.util.ByteUtil;

public class LoraNsMsgSender {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String domain="iot.nplink.com";
		int port=8000;
		String appid="123";
		String username="";
		String password="";
		boolean canRun=true;
		Loger loger=new Loger("LoraNsSend");
		Loger logerLoraSendMsg=new Loger("LoraMsgSend");
		try
		{
			String LORANS_STRING=ConfigCache.getConfigString("LORANS_STRING");
			String[] lorans=ConfigCache.getDeSecurityConfig(LORANS_STRING);
			String[] urls=lorans[0].split(":");
			
			domain=urls[0];
			port=Integer.parseInt(urls[1]);
			appid=urls[2];
			username=lorans[1];
			password=lorans[2];
		}
		catch(Exception e)
		{
			canRun=false;
			loger.error("Lora Wan NetWork Server的配置信息错误!"+e.getMessage());
			e.printStackTrace();
		}
		if(!canRun)
		{
			return;
		}
		
			long lastAt=0;
			loger.debug("尝试连接Lora Wan NetWork Server,连接地址:"+domain+","+port+",用户名:"+username+",appid:"+appid);
	        String msg="{\"as\":{ \"app_id\": \""+appid+"\",\"login_name\": \""+username+"\", \"login_type\": 1, \"login_pwd\": \""+password+"\",\"token\": "+System.currentTimeMillis()+" }}";
	        while(true)
			{
	        	//这里是临时的
	        	while((System.currentTimeMillis()-lastAt)<60000)
	        	{
	        		try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        	lastAt=System.currentTimeMillis();
	        	try
				{
	        	InetAddress address=InetAddress.getByName(domain);
				
				DatagramSocket datagramSocket=new DatagramSocket();
	            byte[] buffer=msg.getBytes();
	            DatagramPacket packet=new DatagramPacket(buffer, buffer.length, address, port);
	            datagramSocket.setSoTimeout(6000); //6秒
	            datagramSocket.send(packet);
	            //接收数据
	            DatagramPacket inputPacket = new DatagramPacket(new byte[512], 512);  
	            datagramSocket.receive(inputPacket);    //第一次应接收到类似下面的登录成功消息
	//            {"as":{"app_id":"123","login_name":"123","token":1509071627349,"login_success":true}}
	            String loginMsg=new String(inputPacket.getData(), 0 , inputPacket.getLength());
	            if(loginMsg.indexOf("true")>0)
	            {
	            	loger.debug("Lora Wan登录成功!");
	            }
	            byte[] cmd=ByteUtil.hexStringToBytes("00FD080D50BBCFA4006204");
	            String cmdbase=Base64.getEncoder().encodeToString(cmd);
	            String setMsg="{ \"app\": { \"moteeui\": \"78e4122a\", \"dir\": \"dn\", \"token\": "+(System.currentTimeMillis()%999)+", \"userdata\": { \"port\": 2, \"payload\": \""+cmdbase+"\" } } }";
	            loger.debug("发送命令:"+setMsg);
	            buffer=setMsg.getBytes();
	            DatagramPacket packetSend=new DatagramPacket(buffer, buffer.length, address, port);
	            datagramSocket.setSoTimeout(30000); //6秒
	            datagramSocket.send(packetSend);
	            datagramSocket.close();
	            loger.debug("发送完成");
				}
	            catch(Exception e)
	    		{
	            	//e.printStackTrace();
	    			//loger.logStackTrace(e.getStackTrace());
	    			loger.error("Lora Wan NetWork Server发送出错!"+e.toString(),e);
	    		}
			}
	}


}

 */
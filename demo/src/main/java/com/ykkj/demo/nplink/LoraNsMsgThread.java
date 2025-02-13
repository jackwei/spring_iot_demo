package com.ykkj.demo.nplink;
/*
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executors;

import com.ykkj.compute.util.ConfigCache;
import com.ykkj.compute.util.Loger;
import com.ykkj.iot.msgqueue.MsgQueue;
import com.ykkj.iot.msgqueue.vo.LoraNsMsg;

public class LoraNsMsgThread  implements Runnable{

	final int MAX_SESSION_TIME=30000;   //30秒
	final int MAX_MSG_SIZE=10240;  //10240 10k单个消息
	
	public static boolean isPause=false;
	public void run() {
		String domain="iot.nplink.com";
		int port=8000;
		String appid="123";
		String username="";
		String password="";
		boolean canRun=true;
		Loger loger=new Loger("LoraNs");
		Loger logerLoraMsg=new Loger("LoraMsg");
		try
		{
			String LORANS_STRING=ConfigCache.getConfigString("LORANS_STRING");
			String[] lorans=ConfigCache.getDeSecurityConfig(LORANS_STRING);
			String[] urls=lorans[0].split(":");
			
			domain=urls[0];
	//		domain="192.168.1.220";
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
		
		DatagramSocket datagramSocket=null;
		try {
			datagramSocket = new DatagramSocket();
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		loger.debug("尝试连接Lora Wan NetWork Server,连接地址:"+domain+","+port+",用户名:"+username+",appid:"+appid);
	        String msg="{\"as\":{ \"app_id\": \""+appid+"\",\"login_name\": \""+username+"\", \"login_type\": 1, \"login_pwd\": \""+password+"\",\"token\": "+System.currentTimeMillis()+" }}";
			while(true)
			{
				if(isPause)
				{
					//暂停接收
					loger.debug("Lora Wan暂停接收!");
					synchronized(this) {
	                    while(isPause) {
	                       try {
							wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                    }
					}
				}
				else
				{
					try
					{

						InetAddress address=InetAddress.getByName(domain);
						if(datagramSocket==null)
						{
							datagramSocket = new DatagramSocket();
						}
			            byte[] buffer=msg.getBytes();
			            DatagramPacket packet=new DatagramPacket(buffer, buffer.length, address, port);
			            datagramSocket.setSoTimeout(6000); //6秒
			            datagramSocket.send(packet);
			            //接收数据
			            DatagramPacket inputPacket = new DatagramPacket(new byte[512], 512);  
			            datagramSocket.receive(inputPacket);    //第一次应接收到类似下面的登录成功消息
//			            {"as":{"app_id":"123","login_name":"123","token":1509071627349,"login_success":true}}
			            String loginMsg=new String(inputPacket.getData(), 0 , inputPacket.getLength());
			            if(loginMsg.indexOf("true")>0)
			            {
			            	loger.debug("Lora Wan登录成功!");
			            	 long at=System.currentTimeMillis();
					            while((System.currentTimeMillis()-at)<MAX_SESSION_TIME)
					            {
					            	try
					            	{
					            		inputPacket = new DatagramPacket(new byte[MAX_MSG_SIZE], MAX_MSG_SIZE);  
					            		int timeout=(int)(MAX_SESSION_TIME-(System.currentTimeMillis()-at));
					            		if(timeout<3000)
					            		{
					            			timeout=3000;
					            		}
					            		else if(timeout>MAX_SESSION_TIME)
					            		{
					            			timeout=MAX_SESSION_TIME;
					            		}
						            	datagramSocket.setSoTimeout(timeout);
							            datagramSocket.receive(inputPacket);  
							            String loraNsMsg=new String(inputPacket.getData(), 0 , inputPacket.getLength());
							            //记录接收日志
							            logerLoraMsg.logTxt(loraNsMsg);
							            LoraNsMsg lnm=new LoraNsMsg();
							            lnm.setMsg(loraNsMsg);
							            //把所有日志放到
							            MsgQueue.addTask(lnm);
					            	}
					            	catch(SocketTimeoutException ex1)
					            	{
					            		loger.debug("Lora Wan NetWork接收消息超时");
					            	}
					            	catch(Exception e)
					            	{
					            		loger.error("Lora Wan NetWork接收消息出错:"+e.toString(),e);
					            	}
					            	
					            }
					           
			            }
			            else
			            {
			            	//防止后续登录时收到有用消息，把日志放到队列进行处理
			            	LoraNsMsg lnm=new LoraNsMsg();
				            lnm.setMsg(loginMsg);
				            MsgQueue.addTask(lnm);
			            	loger.error("Lora Wan NetWork Server登录失败");
			            }
			           
			            //datagramSocket.close();
					}
		            catch(Exception e)
		    		{
		            	//e.printStackTrace();
		    			//loger.logStackTrace(e.getStackTrace());
		            	datagramSocket=null;
		    			loger.error("Lora Wan NetWork Server连接出错!"+e.toString(),e);
		    		}
				}
				
			}
	
		
		
		
	}
	synchronized  public void  setPause()
	{
		isPause=true;
		notify();
	}
	synchronized  public void  setPauseResume()
	{
		isPause=false;
		notify();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoraNsMsgThread l=new LoraNsMsgThread();
		Executors.newCachedThreadPool().submit(l);
		long a=System.currentTimeMillis();
		while(true)
		{
			if((System.currentTimeMillis())-a>10000)
			{
				if(isPause)
				{
					l.setPauseResume();
				}
				else
				{
					isPause=true;
				}
				a=System.currentTimeMillis();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

*/
package com.lou.example;

import cn.hutool.core.thread.ThreadUtil;


import java.util.concurrent.TimeUnit;

public class SADPDeviceSearcher {


    public static void main(String[] args) {

        //System.setProperty("jna.library.path", "D:\\OtherIdeaProjects\\ykkj-iot-demo\\hikvision\\src\\main\\resources\\lib");


        //win系统加载库路径
        //String strDllPath = System.getProperty("user.dir") + "\\hikvision\\src\\main\\resources\\lib\\Sadp.dll";

        //SADPLibrary INSTANCE = Native.load(strDllPath, SADPLibrary.class);

        SADPLibrary.INSTANCE.SADP_SetLogToFile(2,"D://SadpLog",5);



        //设置自动搜索的时间间隔，为0则不自动
//        int interval = SADPLibrary.INSTANCE.SADP_SetAutoRequestInterval(5);
//
//        if(interval != 0){
//            int iError = SADPLibrary.INSTANCE.SADP_GetLastError();
//            System.out.println("SADP_SetAutoRequestInterval error:"+iError);
//        }



//        SADPLibrary.SADP_START_PARAM sadpStartParam = new SADPLibrary.SADP_START_PARAM();
//        sadpStartParam.read();
//        sadpStartParam.pUserData = null;
//        sadpStartParam.psubnetDeviceFindCallback = null;
//        sadpStartParam.pdeviceFindCallbackV40 = new DeviceFindCallBack();
//        sadpStartParam.write();
//        int initResult = SADPLibrary.INSTANCE.SADP_Start_V50(sadpStartParam);

        try {
            // 1. 初始化SADP
            int initResult = SADPLibrary.INSTANCE.SADP_Start_V40(new DeviceFindCallBack(),1,null);

            if (initResult != 1) {
                int iError = SADPLibrary.INSTANCE.SADP_GetLastError();
                System.out.println("SADP_Start_V40 error"+iError);
                return;
            }

            int interval = SADPLibrary.INSTANCE.SADP_SetAutoRequestInterval(9);

            if(interval != 0){
                int iError = SADPLibrary.INSTANCE.SADP_GetLastError();
                System.out.println("SADP_SetAutoRequestInterval error:"+iError);
            }




            int num = 1;
            while (num < 5){

                //        System.out.println("start SADP_SendInquiry");
//                int send = SADPLibrary.INSTANCE.SADP_SendInquiry();
//                System.out.println("send============"+send);
//                if(send != 1){
//                    int iError = SADPLibrary.INSTANCE.SADP_GetLastError();
//                    System.out.println("SADP_SendInquiry error:"+iError);
//                    return;
//                }
//                int clearup = SADPLibrary.INSTANCE.SADP_Clearup();
//                System.out.println("SADP_Clearup"+clearup);

                ThreadUtil.sleep(3000);
                num++;
                System.out.println("num===="+num);

            }


            ThreadUtil.sleep(3, TimeUnit.MINUTES);

            //ThreadUtil.sleep(5, TimeUnit.SECONDS);
            SADPLibrary.INSTANCE.SADP_Stop();


            System.out.println("stop");
            //SADPLibrary.PDEVICE_FIND_CALLBACK_V40


        } finally {
            // 6. 释放资源
            //SADPLibrary.INSTANCE.SADP_Cleanup();
        }
    }


}




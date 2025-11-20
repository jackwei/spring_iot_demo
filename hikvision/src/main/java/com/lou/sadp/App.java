package com.lou.sadp;

import com.lou.example.DeviceFindCallBack;

public class App {


    public static void main( String[] args )
    {

        HCSadpSdkFunc.getInstance().startSearchDevices(new SadpDeviceFindCallBack());
        try {
            Thread.sleep(5*1000);
            System.out.println("refreshDevices");
            HCSadpSdkFunc.getInstance().stopSearchDevices();
            HCSadpSdkFunc.getInstance().startSearchDevices(new SadpDeviceFindCallBack());

            //HCSadpSdkFunc.getInstance().refreshDevices();

            Thread.sleep(20*1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

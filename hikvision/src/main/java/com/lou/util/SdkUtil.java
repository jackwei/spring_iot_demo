package com.lou.util;

import com.lou.model.DeviceInfo;
import com.sun.jna.Pointer;


import java.util.ArrayList;
import java.util.List;

public class SdkUtil {
//
//    // 搜索局域网设备
//    public static List<DeviceInfo> searchDevices() {
//        List<DeviceInfo> deviceList = new ArrayList<>();
//
//        // 搜索回调函数
//        HCNetSDK.fSearchDevicesCallBack_V31 callBack = new HCNetSDK.fSearchDevicesCallBack_V31() {
//            @Override
//            public void invoke(int lSearchHandle, NET_DVR_DEVICEINFO_V31 pDeviceInfo, Pointer pUser) {
//                if (pDeviceInfo != null) {
//                    DeviceInfo device = new DeviceInfo();
//                    device.setIp(new String(pDeviceInfo.sDVRIP).trim());
//                    device.setPort(pDeviceInfo.wDVRPort);
//                    device.setSerialNo(new String(pDeviceInfo.sSerialNumber).trim());
//                    device.setModel(new String(pDeviceInfo.sDVRName).trim());
//                    device.setFailCount(0);
//                    device.setAlive(true);
//                    deviceList.add(device);
//                }
//            }
//        };
//
//        // 开始搜索（0表示搜索所有设备类型）
//        int searchHandle = HCNetSDK.INSTANCE.NET_DVR_SearchDevices_V31(0, callBack, null);
//        if (searchHandle < 0) {
//            System.err.println("搜索失败，错误码：" + HCNetSDK.INSTANCE.NET_DVR_GetLastError());
//            return deviceList;
//        }
//
//        // 等待搜索完成（最多5秒）
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//
//        // 停止搜索
//        HCNetSDK.INSTANCE.NET_DVR_StopSearchDevices(searchHandle);
//        return deviceList;
//    }
//
//    // 登录设备（验证存活）
//    public static boolean loginDevice(String ip, int port, String username, String password) {
//        NET_DVR_DEVICEINFO_V30 deviceInfo = new NET_DVR_DEVICEINFO_V30();
//        int userId = HCNetSDK.INSTANCE.NET_DVR_Login_V30(ip, port, username, password, deviceInfo);
//        if (userId >= 0) {
//            HCNetSDK.INSTANCE.NET_DVR_Logout(userId); // 登录成功后立即退出
//            return true;
//        }
//        return false;
//    }
}

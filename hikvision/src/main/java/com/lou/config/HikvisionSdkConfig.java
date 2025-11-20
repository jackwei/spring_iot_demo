package com.lou.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
public class HikvisionSdkConfig {

//    @Value("${hikvision.sdk.lib-path}")
//    private String libPath;
//
//    // 初始化SDK
//    @PostConstruct
//    public void init() {
//        // 设置SDK库路径
//        System.setProperty("jna.library.path", libPath);
//
//        // 加载SDK
//        if (!HCNetSDK.NET_DVR_Init()) {
//            throw new RuntimeException("海康SDK初始化失败，错误码：" + HCNetSDK.INSTANCE.NET_DVR_GetLastError());
//        }
//
//        // 配置超时参数
//        HCNetSDK.INSTANCE.NET_DVR_SetConnectTime(2000, 1);
//        HCNetSDK.INSTANCE.NET_DVR_SetReconnect(10000, true);
//        System.out.println("海康SDK初始化成功");
//    }
//
//    // 销毁SDK资源
//    @PreDestroy
//    public void destroy() {
//        HCNetSDK.INSTANCE.NET_DVR_Cleanup();
//        System.out.println("海康SDK资源已释放");
//    }
}
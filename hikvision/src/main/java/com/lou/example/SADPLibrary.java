package com.lou.example;

import com.lou.model.SADP_DEVICE_INFO;
import com.sun.jna.*;
import com.sun.jna.ptr.IntByReference;

public interface SADPLibrary extends Library {
    // 加载SADP动态库（Windows为SADP.dll，Linux为libSADP.so）
    //win系统加载库路径
    String strDllPath = System.getProperty("user.dir") + "\\hikvision\\src\\main\\resources\\lib\\Sadp";


    SADPLibrary INSTANCE = (SADPLibrary) Native.loadLibrary(strDllPath, SADPLibrary.class);

    // 初始化SADP模块（返回0成功，其他失败）
    int SADP_Init();

    int SADP_Start_V50(SADP_START_PARAM sadpStartParam);

    int SADP_Start_V40(PDEVICE_FIND_CALLBACK_V40 pDeviceFindCallBack, int bInstallNPF,Pointer pUserData);

    public static interface PDEVICE_FIND_CALLBACK_V40 extends Callback {
        public void invoke(SADPLibrary.SADP_DEVICE_INFO_V40 lpDeviceinfo, Pointer pUserData);
    }

    public static interface PSUBNET_DEVICE_FIND_CALLBACK extends Callback {
        public void invoke(SADPLibrary.SADP_SUBNET_DEVICE_INFO lpDeviceinfo, Pointer pUserData);
    }



    // 开始搜索设备（超时时间ms，返回0成功）
    int SADP_StartDiscover(int iTimeOut);

    int SADP_SendInquiry();

    int SADP_SetAutoRequestInterval(int dwInterval);

    // 获取搜索到的设备数量（pNum为输出参数）
    int SADP_GetDeviceNumber(IntByReference pNum);

    // 获取设备信息（pDeviceInfo为输出数组，iMaxNum为最大数量）
    int SADP_GetDeviceInfo(SADP_DEVICE_INFO[] pDeviceInfo, int iMaxNum);

    // 停止搜索并释放资源
    int SADP_Clearup();

    int SADP_GetLastError();

    void SADP_Stop();

    int SADP_SetLogToFile( int nLogLevel, String strLogDir, int bAutoDe);

    public static class SADP_START_PARAM extends Structure {
        public PDEVICE_FIND_CALLBACK_V40 pdeviceFindCallbackV40;
        public PSUBNET_DEVICE_FIND_CALLBACK psubnetDeviceFindCallback;
        public Pointer pUserData;
        public byte byRes ;
    }

    public static class SADP_DEVICE_INFO extends Structure {
        public  byte[]    szSeries = new byte[12];
        public  byte[]    szSerialNO = new byte[48];
        public  byte[]    szMAC = new byte[20];
        public  byte[]    szIPv4Address = new byte[16];
        public  byte[]    szIPv4SubnetMask = new byte[16];
        public  int       dwDeviceType;
        public  int       dwPort;
        public  int       dwNumberOfEncoders;
        public  int       dwNumberOfHardDisk;
        public  byte[]    szDeviceSoftwareVersion = new byte[48];
        public  byte[]    szDSPVersion = new byte[48];
        public  byte[]    szBootTime = new byte[48];
        public  int       iResult;
        public  byte[]    szDevDesc = new byte[24];
        public  byte[]    szOEMinfo = new byte[24];
        public  byte[]    szIPv4Gateway = new byte[16];
        public  byte[]    szIPv6Address = new byte[46];
        public  byte[]    szIPv6Gateway = new byte[46];
        public  byte      byIPv6MaskLen;
        public  byte      bySupport;
        public  byte      byDhcpEnabled;
        public  byte      byDeviceAbility;
        public  byte      wHttpPort;
        public  short     wDigitalChannelNum;
        public  byte[]    szCmsIPv4 = new byte[16];
        public  short     wCmsPort;
        public  byte      byOEMCode;
        public  byte      byActivated;
        public  byte[]    szBaseDesc = new byte[24];
        //public  byte[]    byRes = new byte[16];

        public byte   bySupport1;

        public byte   byHCPlatform;          //是否支持HCPlatform 0-保留, 1-支持, 2-不支持
        public byte   byEnableHCPlatform;    //是否启用HCPlatform  0-保留, 1-启用， 2-不启用
        public byte    byEZVIZCode;           //0-基线设备, 1-萤石设备
        public int    dwDetailOEMCode;       //详细OEMCode信息:oemcode由客户序号（可变位,从1开始，1~429496)+菜单风格（2位）+区域号（2位）三部分构成。

        public byte    byModifyVerificationCode; //是否修改验证码 0-保留， 1-修改验证码， 2-不修改验证码
        public byte    byMaxBindNum;          //支持绑定的最大个数（目前只有NVR支持该字段）
        public  short  wOEMCommandPort;       //OEM命令端口
        public byte    bySupportWifiRegion;

        public byte    byEnableWifiEnhancement;//是否启用wifi增强模式,0-不启用，1-启用
        public byte    byWifiRegion;           //设备当前区域，0-default，1-china，2-nothAmerica，3-japan，4-europe,5-world
        public byte    bySupport2;
    }
    public class SADP_SUBNET_DEVICE_INFO extends Structure {
        public int dwDeviceType;
        public byte[] szDevDesc = new byte[64];
        public byte[] szSerialNO = new byte[128];
        public byte[] szIPv4Address = new byte[16];
        public byte[] szIPv4SubnetMask = new byte[16];
        public byte[] szIPv4Gateway = new byte[16];
        public byte[] szIPv6Address = new byte[46];
        public byte[] szIPv6Gateway = new byte[46];
        public byte byIPv6MaskLen;
        public byte bySupportIPv6;
        public byte bySupportModifyIPv6;
        public byte bySupportDhcp;
        public byte byDhcpEnabled;
        public byte[] byRes = new byte[1024];

    }


    public class SADP_DEVICE_INFO_V40 extends Structure {

        public SADP_DEVICE_INFO struSadpDeviceInfo;
        public byte   byLicensed;          //设备是否授权：0-保留,1-设备未授权，2-设备已授权
        public byte    bySystemMode;        //系统模式 0-保留,1-单控，2-双控，3-单机集群，4-双控集群
        public byte    byControllerType;    //控制器类型 0-保留，1-A控，2-B控
        public byte[]            szEhmoeVersion =new byte[16];  //Ehmoe版本号
        public byte   bySpecificDeviceType;//设备类型，1-中性设备  2-海康设备
        public  int    dwSDKOverTLSPort;    //私有协议中 SDK Over TLS 命令端口
        public byte    bySecurityMode;      //设备安全模式：0-standard,1-high-A,2-high-B,3-custom
        public byte    bySDKServerStatus;   //设备SDK服务状态, 0-开启，1-关闭
        public byte    bySDKOverTLSServerStatus;         //设备SDKOverTLS服务状态, 0-关闭，1-开启
        public byte[]            szUserName=new byte[33]; //管理员用户的用户名（设备安全模式在非标准模式下是允许用户设置管理员用户的用户名，标准模式默认为admin）
        public byte[]            szWifiMAC=new byte[20];       //设备所连wifi的Mac地址
        public byte    byDataFromMulticast; //0-链路 1-多播
        public byte    bySupportEzvizUnbind; //是否支持萤石解绑 0-不支持 1-支持
        public byte    bySupportCodeEncrypt; //是否支持重置口令AES128_ECB解密  0-不支持 1-支持
        public byte    bySupportPasswordResetType; //是否支持获取密码重置方式参数  0-不支持 1-支持
        public byte    byEZVIZBindStatus; //设备萤石云绑定状态,0-未知,1-已绑定,2-未绑定
        public byte            szPhysicalAccessVerification ; //设备支持的物理接触式添加方式,1#AP配网传递,2#用户令牌（用户token）绑定,3#物理按键接触,4#扫码绑定（设备token）
        public byte[]    byRes = new byte[183];

//
//        public static class ByReference extends SADP_DEVICE_INFO_V40 implements Structure.ByReference{
//        }
//
//        public static class ByValue extends SADP_DEVICE_INFO_V40 implements Structure.ByValue{
//        }

    }

    public static class SADP_DEV_NET_PARAM extends Structure {

        public  byte[]    szIPv4Address = new byte[16];
        public  byte[]    szIPv4SubnetMask = new byte[16];
        public  byte[]    szIPv4Gateway = new byte[16];
        public  byte[]    szIPv6Address = new byte[128];
        public  byte[]    szIPv6Gateway = new byte[128];
        public  short        wPort;
        public  byte         byIPv6MaskLen;
        public  byte         byDhcpEnable;
        public  short        wHttpPort;
        public  byte[]       byRes = new byte[126];

        public static class ByReference extends SADP_DEV_NET_PARAM implements Structure.ByReference{
        }

        public static class ByValue extends SADP_DEV_NET_PARAM implements Structure.ByValue{
        }
    }


}
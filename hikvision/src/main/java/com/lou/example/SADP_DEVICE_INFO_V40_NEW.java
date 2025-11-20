package com.lou.example;

import com.lou.model.SADP_DEVICE_INFO;
import com.sun.jna.Structure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class SADP_DEVICE_INFO_V40_NEW extends Structure {

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
    public byte[]            szPhysicalAccessVerification; //设备支持的物理接触式添加方式,1#AP配网传递,2#用户令牌（用户token）绑定,3#物理按键接触,4#扫码绑定（设备token）
    public byte[]    byRes;

    public static class ByReference extends SADP_DEVICE_INFO_V40_NEW implements Structure.ByReference{
        public ByReference(SADP_DEVICE_INFO struSadpDeviceInfo, byte byLicensed, byte bySystemMode, byte byControllerType, byte[] szEhmoeVersion, byte bySpecificDeviceType, int dwSDKOverTLSPort, byte bySecurityMode, byte bySDKServerStatus, byte bySDKOverTLSServerStatus, byte[] szUserName, byte[] szWifiMAC, byte byDataFromMulticast, byte bySupportEzvizUnbind, byte bySupportCodeEncrypt, byte bySupportPasswordResetType, byte byEZVIZBindStatus, byte[] szPhysicalAccessVerification, byte[] byRes) {
            super(struSadpDeviceInfo, byLicensed, bySystemMode, byControllerType, szEhmoeVersion, bySpecificDeviceType, dwSDKOverTLSPort, bySecurityMode, bySDKServerStatus, bySDKOverTLSServerStatus, szUserName, szWifiMAC, byDataFromMulticast, bySupportEzvizUnbind, bySupportCodeEncrypt, bySupportPasswordResetType, byEZVIZBindStatus, szPhysicalAccessVerification, byRes);
        }
    }

    public static class ByValue extends SADP_DEVICE_INFO_V40_NEW implements Structure.ByValue{
        public ByValue(SADP_DEVICE_INFO struSadpDeviceInfo, byte byLicensed, byte bySystemMode, byte byControllerType, byte[] szEhmoeVersion, byte bySpecificDeviceType, int dwSDKOverTLSPort, byte bySecurityMode, byte bySDKServerStatus, byte bySDKOverTLSServerStatus, byte[] szUserName, byte[] szWifiMAC, byte byDataFromMulticast, byte bySupportEzvizUnbind, byte bySupportCodeEncrypt, byte bySupportPasswordResetType, byte byEZVIZBindStatus, byte[] szPhysicalAccessVerification, byte[] byRes) {
            super(struSadpDeviceInfo, byLicensed, bySystemMode, byControllerType, szEhmoeVersion, bySpecificDeviceType, dwSDKOverTLSPort, bySecurityMode, bySDKServerStatus, bySDKOverTLSServerStatus, szUserName, szWifiMAC, byDataFromMulticast, bySupportEzvizUnbind, bySupportCodeEncrypt, bySupportPasswordResetType, byEZVIZBindStatus, szPhysicalAccessVerification, byRes);
        }
    }
}

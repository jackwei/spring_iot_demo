package com.lou.model;

import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;
import java.util.Arrays;
import java.util.List;

// 设备信息结构体（对应SADP_DEVICE_INFO）
public class SADP_DEVICE_INFO extends Structure {
    public byte[] sSerialNumber = new byte[48]; // 设备序列号
    public byte[] sIPv4Address = new byte[16]; // IPv4地址
    public byte[] sIPv4SubnetMask = new byte[16]; // 子网掩码
    public byte[] sIPv4Gateway = new byte[16]; // 网关
    public byte[] sMACAddress = new byte[24]; // MAC地址
    public int iDeviceType; // 设备类型
    public byte[] sDeviceName = new byte[128]; // 设备名称
    public int iPort; // 端口号
    // 其他字段根据SDK版本补充（参考头文件）

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("sSerialNumber", "sIPv4Address", "sIPv4SubnetMask",
                "sIPv4Gateway", "sMACAddress", "iDeviceType",
                "sDeviceName", "iPort");
    }
}
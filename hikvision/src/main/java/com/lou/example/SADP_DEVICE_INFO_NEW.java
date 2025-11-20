package com.lou.example;

import com.sun.jna.Structure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class SADP_DEVICE_INFO_NEW extends Structure {

    public byte[]      szSeries = new byte[12];          //设备系列（保留）
    public byte[]            szSerialNO = new byte[48];        //设备序列号
    public byte[]             szMAC  = new byte[20];             //设备物理地址
    public byte[]             szIPv4Address  = new byte[16];     //设备IPv4地址
    public byte[]             szIPv4SubnetMask = new byte[16];  //设备IPv4子网掩码
    public int    dwDeviceType;          //设备类型，具体数值代表的设备型号
    public int    dwPort;                //设备网络SDK服务端口号(默认8000)
    public int    dwNumberOfEncoders;    //设备编码器个数，即设备编码通道个数。对于解码器，其值设为0
    public int    dwNumberOfHardDisk;    //设备硬盘数目
    public byte[]            szDeviceSoftwareVersion  = new byte[48];  //设备软件版本号
    public byte[]            szDSPVersion  = new byte[48];      //设备DSP版本号
    public byte[]            szBootTime  = new byte[48];        //开机时间
    public int             iResult;
    //信息类型： 1.设备上线  2.设备更新  3.设备下线  4.设备重启  5.设备更新失败
    //SADP_ADD        1   新设备上线，之前在SADP库列表中未出现的设备
    //SADP_UPDATE     2   在线的设备的网络参数或者某些状态改变
    //SADP_DEC        3   设备下线，设备自动发送下线消息或120秒内检测不到设备
    //SADP_RESTART    4   之前SADP库列表中出现过之后下线的设备再次上线
    //SADP_UPDATEFAIL 5   设备更新失败
    public byte[]            szDevDesc = new byte[24];         //设备类型描述 与dwDeviceType对应
    public byte[]            szOEMinfo  = new byte[24] ;         //OEM产商信息
    public byte[]            szIPv4Gateway  = new byte[16];     //IPv4网关
    public byte[]            szIPv6Address  = new byte[46];     //IPv6地址
    public byte[]            szIPv6Gateway = new byte[46];     //IPv6网关
    public byte   byIPv6MaskLen;         //IPv6子网前缀长度
    public byte   bySupport;
    //按位表示,对应为为1表示支持
    //0x01:是否支持Ipv6
    //0x02:是否支持修改Ipv6参数
    //0x04:是否支持Dhcp
    //0x08:是否支持udp多播
    //0x10:是否含加密节点
    //0x20:是否支持恢复密码
    //0x40:是否支持重置密码
    //0x80:是否支持同步IPC密码
    public byte   byDhcpEnabled;         //Dhcp状态, 0 不启用 1 启用
    public byte   byDeviceAbility;       //设备能力
    //0：设备不支持“‘设备类型描述’ 'OEM厂商' 'IPv4网关' ‘IPv6地址’ 'IPv6网关' ‘IPv6子网前缀’‘DHCP’”
    //1：支持上诉功能
    public int  wHttpPort ;             //Http 端口
    public int  wDigitalChannelNum ;    //数字通道数
    public byte[]            szCmsIPv4  = new byte[16];         //CMS注册服务器IPv4地址
    public int  wCmsPort;              //CMS注册服务器监听端口
    public byte   byOEMCode;             //0-基线设备 1-OEM设备
    public byte   byActivated;           //设备是否激活;0-激活，1-未激活（老的设备都是已激活状态）
    public byte[]            szBaseDesc = new byte[24];        //基线短型号，不随定制而修改的型号，用于萤石平台进行型号对比
    public byte   bySupport1;
    //按位表示,  1表示支持，0表示不支持
    //0x01:是否支持重置密码方式2
    //0x02;是否支持设备锁定功能
    //0x04:是否支持导入GUID重置密码
    //0x08:是否支持安全问题重置密码
    //0x10:是否支持OEM更换Logo
    //0x20:是否支持绑定操作
    //0x40:是否支持恢复未激活
    //0x80:是否支持wifi信号增强模式
    public byte   byHCPlatform;          //是否支持HCPlatform 0-保留, 1-支持, 2-不支持
    public byte   byEnableHCPlatform;    //是否启用HCPlatform  0-保留, 1-启用， 2-不启用
    public byte    byEZVIZCode;           //0-基线设备, 1-萤石设备
    public int    dwDetailOEMCode;       //详细OEMCode信息:oemcode由客户序号（可变位,从1开始，1~429496)+菜单风格（2位）+区域号（2位）三部分构成。
    //规则说明：oemcode最大值为4294967295，最多是十位数。
    //0: 老设备
    //1: 新基线设备
    //10101: 有具体OEM code的为OEM设备
    public byte    byModifyVerificationCode; //是否修改验证码 0-保留， 1-修改验证码， 2-不修改验证码
    public byte    byMaxBindNum;          //支持绑定的最大个数（目前只有NVR支持该字段）
    public  int  wOEMCommandPort;       //OEM命令端口
    public byte    bySupportWifiRegion;
    //设备支持的wifi区域列表，按位表示，1表示支持，0表示不支持
    //0x01:是否支持default（默认功率和北美一致）
    //0x02:是否支持china
    //0x04:是否支持nothAmerica
    //0x08:是否支持japan
    //0x10:是否支持europe
    //0x20:是否支持world
    public byte    byEnableWifiEnhancement;//是否启用wifi增强模式,0-不启用，1-启用
    public byte    byWifiRegion;           //设备当前区域，0-default，1-china，2-nothAmerica，3-japan，4-europe,5-world
    public byte    bySupport2;
}

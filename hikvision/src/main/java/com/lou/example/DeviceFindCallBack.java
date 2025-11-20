package com.lou.example;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.lou.util.ConvertUtil;
import com.sun.jna.Pointer;

import java.util.Map;

public class DeviceFindCallBack implements SADPLibrary.PDEVICE_FIND_CALLBACK_V40 {


    @Override
    public void invoke(SADPLibrary.SADP_DEVICE_INFO_V40 lpDeviceinfo, Pointer pUserData) {

        System.out.println(DateUtil.now());
        //System.out.println(lpDeviceinfo);
        Map deviceMap = ConvertUtil.struct2Map(lpDeviceinfo);
        Map infoStr = (Map) deviceMap.get("struSadpDeviceInfo");

        System.out.println(infoStr.get("szIPv4Address"));
        System.out.println(infoStr.get("szMAC"));
        System.out.println(infoStr.get("szSerialNO"));
        System.out.println(infoStr.get("iResult"));

        int clearup = SADPLibrary.INSTANCE.SADP_Clearup();
        System.out.println("SADP_Clearup"+clearup);

        //System.out.println(JSONUtil.toJsonPrettyStr(ConvertUtil.struct2Map(lpDeviceinfo)));

    }
}

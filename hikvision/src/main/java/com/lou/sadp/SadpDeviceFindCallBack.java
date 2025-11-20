package com.lou.sadp;

import cn.hutool.json.JSONUtil;
import com.lou.util.ConvertUtil;
import com.sun.jna.Pointer;

public class SadpDeviceFindCallBack implements HCSadpSdk.PDEVICE_FIND_CALLBACK {

    @Override
    public void invoke(HCSadpSdk.SADP_DEVICE_INFO lpDeviceInfo, Pointer pUserData) {
        System.out.println(JSONUtil.toJsonPrettyStr(ConvertUtil.struct2Map(lpDeviceInfo)));
    }

}

package com.lou.sadp;

import cn.hutool.setting.dialect.Props;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class SdkErrorUtil {
	private  static Props HkNetErrorProps;
	private  static Props HkSadpErrorProps;
	private  final static String HK_NET_ERROR_PREFIX="HK_NET_ERROR_";
	private  final static String HK_SADP_ERROR_PREFIX="HK_SADP_ERROR_";
	
	private static Props getHkNetErrorProps(){
		if(HkNetErrorProps==null){
			HkNetErrorProps=new Props("hk_net_error.properties");
		}
		return HkNetErrorProps;
	}
	
	private static Props getHkSadpErrorProps(){
		if(HkSadpErrorProps==null){
			HkSadpErrorProps=new Props("hk_sadp_error.properties");
		}
		return HkSadpErrorProps;
	}

	
	public static String getHCSadpErrorMsg(){
		int code=HCSadpSdkFunc.sadpSdk.SADP_GetLastError();
		if(code!=0){
			String error=getHkSadpErrorProps().getStr(HK_SADP_ERROR_PREFIX+code);
			log.info(code+"--"+error);
			return error; 
		}
		return null; 
	}

}

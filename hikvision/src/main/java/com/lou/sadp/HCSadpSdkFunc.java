package com.lou.sadp;

import cn.hutool.core.lang.Assert;
import cn.hutool.log.Log;
import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @ClassName:  HkSadpSdkFunc   
 * @Description: 设备网络搜索SDK函数封装 
 * @author: ShenYue 
 * @date:   2019年4月23日 下午4:38:27
 */
@Slf4j
public class HCSadpSdkFunc implements SdkFunc{

	public  static HCSadpSdk sadpSdk = HCSadpSdk.INSTANCE;
	private static boolean isInit = false;
	private boolean isSearching = false;
	
	private static class HCSadpSdkFuncInstance {
        private static final HCSadpSdkFunc INSTANCE = new HCSadpSdkFunc();
    }

    public static HCSadpSdkFunc getInstance() {
        return HCSadpSdkFuncInstance.INSTANCE;
    }
	
	private HCSadpSdkFunc() {
		super();
		init();
	}

	@Override
	public void init() {
		if(!isInit){
			sadpSdk.SADP_SetLogToFile(3,SdkPath.ROOT_PATH+"\\log",1);
			isInit=true;
			//log.info("HCSadpSdk init success.");
		}
	}
	
	/**
	 * 开始搜索网络设备
	 */
	public boolean startSearchDevices(HCSadpSdk.PDEVICE_FIND_CALLBACK callBack){
		Assert.isFalse(isSearching,"device must stop searching first.");
		log.info("HkSadpSdk search devices.");
		boolean isSuccess=sadpSdk.SADP_Start_V30(callBack,1,null);
		if(isSuccess){
			log.info("searching devices start.");
			isSearching=true;
			return true;
		}else{
			log.info(SdkErrorUtil.getHCSadpErrorMsg());
			return false;
		}
	}
	
	/**
	 * 手动刷新网络设备
	 */
	public boolean refreshDevices(){
		Assert.isTrue(isSearching,"must start devices searching first.");
		log.info("refresh devices.");
		boolean isSuccess=sadpSdk.SADP_SendInquiry();
		if(isSuccess){
			log.info("refresh devices success.");
			return true;
		}else{
			log.info(SdkErrorUtil.getHCSadpErrorMsg());
			return false;
		}
	}
	
	/**
	 * 停止搜索网络设备
	 * @return 
	 */
	public  boolean stopSearchDevices(){
		Assert.isTrue(isSearching,"devices searching not start.");
		log.info("stop search devices.");
		boolean isSuccess=sadpSdk.SADP_Stop();
		if(isSuccess){
			log.info("stop search devices success.");
			isSearching=false;
			return true;
		}else{
			log.info(SdkErrorUtil.getHCSadpErrorMsg());
			return false;
		}
	}
	



	@Override
	public boolean isSuccess() {
		return false;
	}

}

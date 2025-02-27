package com.ykkj.license.verify;

import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseParam;

public class LicenseManagerHolder {

    private static volatile LicenseManager LICENSE_MANAGER;
    public static LicenseManager getInstance(LicenseParam param){
        if(LICENSE_MANAGER == null){
            synchronized (LicenseManagerHolder.class){
                if(LICENSE_MANAGER == null){
                    LICENSE_MANAGER = new LicenseManager(param);
                }
            }
        }
        return LICENSE_MANAGER;
    }

}

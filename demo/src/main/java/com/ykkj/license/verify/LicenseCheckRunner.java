package com.ykkj.license.verify;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.nio.file.Paths;

@Slf4j
//@Component
public class LicenseCheckRunner implements ApplicationRunner {

    /**
     * 证书subject
     */
    @Value("${license.subject}")
    private String subject;

    /**
     * 公钥别称
     */
    @Value("${license.publicAlias}")
    private String publicAlias;

    /**
     * 访问公钥库的密码
     */
    @Value("${license.storePass}")
    private String storePass;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("++++++++ 开始安装证书 ++++++++");
        LicenseVerifyParam param = new LicenseVerifyParam();
        param.setSubject(subject);
        param.setPublicAlias(publicAlias);
        param.setStorePass(storePass);
        // 相对路径resources资源目录
        URI uri = getClass().getClassLoader().getResource("").toURI();
        String resourcePath = Paths.get(uri).toString();
        //String resourcePath = getClass().getClassLoader().getResource("").getPath();

        //String resourcePath =  System.getProperty("user.dir")+"/demo/src/main/resources/";
        // 证书地址
        param.setLicensePath(resourcePath + "license.lic");
        // 公钥地址
        param.setPublicKeysStorePath(resourcePath + "publicCerts.keystore");
        // 安装证书
        LicenseVerify licenseVerify = new LicenseVerify();
        licenseVerify.install(param);
        log.info("++++++++ 证书安装结束 ++++++++");
    }

}

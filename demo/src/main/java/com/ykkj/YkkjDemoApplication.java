package com.ykkj;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.flowable.spring.boot.idm.IdmEngineServicesAutoConfiguration;
import org.flowable.ui.idm.conf.IdmSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author lw
 * @version 1.0.0
 * @ClassName YkkjDemoApplication.java
 * @Description TODO
 * @createTime 2021年11月18日
 */
@SpringBootApplication
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class YkkjDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(YkkjDemoApplication.class, args);
    }

}

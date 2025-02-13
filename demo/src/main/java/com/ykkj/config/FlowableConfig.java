package com.ykkj.config;

import com.ykkj.flow.service.CustomIdmIdentityServiceImpl;

import org.flowable.idm.spring.SpringIdmEngineConfiguration;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.context.annotation.Bean;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName FlowableConfig.java
 * @Description TODO
 * @createTime 2021年12月09日
 */
//@DependsOn("databaseInitialize")
//@Configuration
public class FlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {


    @Override
    public void configure(SpringProcessEngineConfiguration engineConfiguration) {
        engineConfiguration.setActivityFontName("宋体");
        engineConfiguration.setLabelFontName("宋体");
        engineConfiguration.setAnnotationFontName("宋体");
    }

    @Bean
    public EngineConfigurationConfigurer<SpringIdmEngineConfiguration> idmEngineConfigurationConfigurer() {
        return idmEngineConfiguration -> idmEngineConfiguration.setIdmIdentityService(
                new CustomIdmIdentityServiceImpl(idmEngineConfiguration));
    }
}


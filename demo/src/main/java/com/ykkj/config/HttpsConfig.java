package com.ykkj.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * HTTPS 配置类
 * </p>
 *
 * @author LW
 * @date Created in 2020/09/07 10:31
 */
@Configuration
@ConditionalOnProperty(value = "server.ssl.enabled")
public class HttpsConfig {


    @Value("${server.port}")
    int redirectPort;

    /**
     * 配置 http(80) -> 强制跳转到 https(443)
     */
    @Bean
    public Connector connector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8022);
        connector.setSecure(false);//true： http使用http, https使用https; //false： http重定向到https;
        connector.setRedirectPort(redirectPort);
        return connector;
    }

    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory(Connector connector) {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;
    }
}

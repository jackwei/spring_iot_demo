package com.ykkj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName SecurityConfiguration.java
 * @Description TODO
 * @createTime 2021年12月15日
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //super.configure(http);
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic();
        //.disable();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//                .withUser("admin")
//                .password("test")
//                .authorities(DefaultPrivileges.ACCESS_TASK,DefaultPrivileges.ACCESS_ADMIN,DefaultPrivileges.ACCESS_IDM,DefaultPrivileges.ACCESS_MODELER,DefaultPrivileges.ACCESS_REST_API)
//                .roles("ADMIN","USER");
//    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("admin") // 管理员，同事具有 ADMIN,USER权限，可以访问所有资源
//                .password("test")  //
//                .authorities(DefaultPrivileges.ACCESS_TASK,DefaultPrivileges.ACCESS_ADMIN,DefaultPrivileges.ACCESS_IDM,DefaultPrivileges.ACCESS_MODELER,DefaultPrivileges.ACCESS_REST_API)
//                .roles("ADMIN", "USER")
//                .and()
//                .withUser("user1").password("{noop}user1") // 普通用户，只能访问 /product/**
//                .roles("USER");
//
//    }


}

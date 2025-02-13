package com.ykkj.config;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName FlowableFilter.java
 * @Description TODO
 * @createTime 2021年12月15日
 */
public class FlowableFilter extends GenericFilterBean {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }
}

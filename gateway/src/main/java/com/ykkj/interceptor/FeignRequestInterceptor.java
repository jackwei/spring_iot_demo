package com.ykkj.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName FeignRequestInterceptor.java
 * @Description TODO
 * @createTime 2023年01月13日
 */
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        // 从header获取X-token
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attr = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attr.getRequest();
        String token = request.getHeader("x-auth-token");//网关传过来的 token
        if (StringUtils.hasText(token)) {
            template.header("X-AUTH-TOKEN", token);
        }
    }
}
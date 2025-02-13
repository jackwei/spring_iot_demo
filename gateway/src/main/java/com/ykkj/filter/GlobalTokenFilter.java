package com.ykkj.filter;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName GlobalTokenFilter.java
 * @Description TODO
 * @createTime 2023年01月12日
 */
@Component
public class GlobalTokenFilter implements GlobalFilter, Ordered {

    private static final String PATH = "/login";
    public static final String SECRET = "develop-Platform";            // JWT密钥
    public static final String TOKEN_KEY = "Authorization";// 存放Token的Header Key

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // 获取请求路径
        String path = request.getPath().toString();
        //登录请求直接到认证服务
        if(path.contains(PATH)){
            return chain.filter(exchange);
        }

        //验证token
        HttpHeaders headers = request.getHeaders();
        List<String> headerList = headers.get(TOKEN_KEY);
        if(headerList.isEmpty()){
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            String msg = "无token，请重新登录";
            DataBuffer buffer = response.bufferFactory().wrap(msg.getBytes());
            return response.writeWith(Mono.just(buffer));
        }

        String token = headerList.get(0);
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }

        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new RuntimeException("401");
        }

        ServerHttpRequest host = request.mutate().header("userId",userId).header("userToken",token).build();

        //将request变成change对象
        ServerWebExchange build = exchange.mutate().request(host).build();
        return chain.filter(build);
    }

    //实现Ordered接口保证优先级，值越小加载的优先级越高
    @Override
    public int getOrder() {
        return -1;
    }
}

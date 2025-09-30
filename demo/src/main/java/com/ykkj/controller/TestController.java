package com.ykkj.controller;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName TestController.java
 * @Description TODO
 * @createTime 2022年09月08日
 */

import com.ykkj.annotation.RateLimiter;
import com.ykkj.enums.LimitType;
import com.ykkj.model.ResultMsg;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jae
 * @date 2022/8/26 14:03
 * @description: 描述
 */
@RestController
public class TestController {

    @RateLimiter(count = 1, limitType = LimitType.IP, limitMsg = "一分钟内只能请求一次，请稍后重试")
    @GetMapping("/hello")
    public ResultMsg hello() {
        return ResultMsg.success("Hello World!");
    }
}

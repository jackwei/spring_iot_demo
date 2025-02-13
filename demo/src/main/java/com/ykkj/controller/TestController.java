package com.ykkj.controller;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName TestController.java
 * @Description TODO
 * @createTime 2022年09月08日
 */

import com.ykkj.annotation.AdminUserVO;
import com.ykkj.annotation.RateLimiter;
import com.ykkj.enums.LimitType;
import com.ykkj.model.ResultMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jack
 * @date 2022/8/26 14:03
 * @description: 描述
 */
@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

    @RateLimiter(count = 1, limitType = LimitType.IP, limitMsg = "一分钟内只能请求一次，请稍后重试")
    @GetMapping("/hello")
    public ResultMsg hello() {
        log.error("message");
        return ResultMsg.success("Hello World!");
    }

    @RequestMapping(value = "/data",method = {RequestMethod.POST,RequestMethod.GET})
    public ResultMsg getData(String message) {
        System.out.println(message);
        log.error(message);
        return ResultMsg.success("Hello World!");
    }

    @RequestMapping(value = "/transfer",method = {RequestMethod.POST,RequestMethod.GET})
    public ResultMsg getTransfer(String message) {

        AdminUserVO adminUserVO = new AdminUserVO();
        adminUserVO.setUserName(message);
        adminUserVO.setId(11);
        adminUserVO.setFaceUrl("face");
        return ResultMsg.success(adminUserVO);
    }
}

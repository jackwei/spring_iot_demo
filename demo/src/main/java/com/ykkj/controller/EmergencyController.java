package com.ykkj.controller;

import com.ykkj.service.TestService;
import com.ykkj.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
public class EmergencyController {

    @Resource
    private TestService testService;

    @GetMapping("/test")
    public void test(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain;charset=utf-8");
        try {
            boolean result = testService.emergencyOperation(response);

            response.getWriter().println(ResultUtil.success("应急任务处理成功!").toString());
        } catch (IOException e) {
            log.error("应急任务处理失败！", e);
            response.getWriter().println(ResultUtil.error("应急任务处理失败!").toString());
        }
    }
}

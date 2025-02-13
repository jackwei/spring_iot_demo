package com.ykkj.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Service
@Slf4j
public class TestService {


    public boolean emergencyOperation(HttpServletResponse response) throws IOException {
        // 如果是定时任务，则该参数传入null，不在终端输出
        boolean canOutput = response != null;
        PrintWriter writer = createPrintWriter(canOutput, response);
        log.info("开始执行应急操作任务");
        output(canOutput, writer, "开始执行应急操作任务");
        for (int i = 0; i < 20; i++) {
            output(canOutput, writer, "完成第" + (i+1) + "批次");
            log.info("完成第 {} 批次", i+1);
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                log.warn("应急操作任务失败");
                output(canOutput, writer, "应急操作任务失败");
                return false;
            }
        }
        log.info("完成应急操作任务");
        output(canOutput, writer, "应急操作任务完成");
        return true;
    }

    private void output(boolean output, PrintWriter writer, String message) throws IOException {
        if (!output) {
            return;
        }
        writer.println(message);
        writer.flush();
    }

    private PrintWriter createPrintWriter(boolean output, HttpServletResponse response) throws IOException {
        if (output) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain;charset=utf-8");
            return response.getWriter();
        }
        return null;
    }

}

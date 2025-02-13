package com.ykkj.controller;


import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author lw
 * @date 2023/1/05 14:03
 * @description: 描述
 */
@Slf4j
@RequestMapping("/api")
@RestController
public class HqKhPortalController {


    @RequestMapping(value = "/sso/getToken", method = {RequestMethod.POST,RequestMethod.GET})
    public String getToken(){
        JSONObject jsonObject = new JSONObject();

        JSONObject accessToken = new JSONObject();
        accessToken.set("accessToken",IdUtil.randomUUID());
        accessToken.set("expiresIn",60000);

        jsonObject.set("result",accessToken);
        return jsonObject.toString();

    }

    @RequestMapping(value = "/service/sys/addTask", method = {RequestMethod.POST,RequestMethod.GET})
    public String AddTask(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("success",true);
        jsonObject.set("message","代办信息添加成功");
        jsonObject.set("code",200);
        jsonObject.set("result", IdUtil.randomUUID());
        jsonObject.set("timestamp",new Date());
        return jsonObject.toString();

    }

    @RequestMapping(value = "/service/sys/taskHandle", method = RequestMethod.GET)
    public String TaskHandle(String taskId){
        log.info("taskHandle{}",taskId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("success",true);
        jsonObject.set("message","代办信息处理成功");
        jsonObject.set("code",200);
        jsonObject.set("result", "taskHandle");
        jsonObject.set("timestamp",new Date());
        return jsonObject.toString();
    }

    @RequestMapping(value = "/service/sys/deleteTask", method = RequestMethod.GET)
    public String DeleteTask(String taskId){
        log.info("deleteTask{}",taskId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("success",true);
        jsonObject.set("message","代办信息删除成功");
        jsonObject.set("code",200);
        jsonObject.set("result", "deleteTask");
        jsonObject.set("timestamp",new Date());
        return jsonObject.toString();
    }

}



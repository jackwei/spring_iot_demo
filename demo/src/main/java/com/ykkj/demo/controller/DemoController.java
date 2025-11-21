package com.ykkj.demo.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ykkj.demo.lierda.PushDataUtils;
import com.ykkj.demo.nplink.ByteUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName DemoController.java
 * @Description TODO
 * @createTime 2021年11月18日
 */
@Slf4j
//@RestController
//@RequestMapping("/uwb")
public class DemoController {

    @GetMapping("/")
    public String sayHello(@RequestParam(required = false, name = "who") String who,HttpServletResponse response) throws IOException {
        if (StrUtil.isBlank(who)) {
            who = "World";
        }
        //identityService.createUserQuery().userId("admin").count();
        //response.sendRedirect("/modeler-app/rest/models");
        return StrUtil.format("Hello, {}!", who);
    }

    @ResponseBody
    @RequestMapping(value = "/uwb-data", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void getJSONByUwbData(@RequestBody JSONObject jsonParam) throws Exception {
        log.info("data===="+jsonParam);
        FileUtil.appendString(jsonParam+FileUtil.getLineSeparator(),"F://uwb.txt","UTF-8");
        log.info("type==={}",jsonParam.getStr("type"));
        if(null !=jsonParam.getJSONObject("content")){
            long timestamp = jsonParam.getJSONObject("content").getLong("timestamp");
            byte[] data = PushDataUtils.checkAndDecrypt(jsonParam.getJSONObject("content").getStr("data"),timestamp);
            log.info(ByteUtil.bytes2HexString(data));
        }

    }

    @Deprecated
    @ResponseBody
    @RequestMapping(value = "/uwb-data1", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public void getUwbData1(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("data");
        JSONObject jsonParam = getJSONParam(request);
        log.info(""+jsonParam);
        FileUtil.appendString(jsonParam+FileUtil.getLineSeparator(),"F://uwb.txt","UTF-8");
        log.info("type==={}",jsonParam.getStr("type"));
        if(null !=jsonParam.getJSONObject("content")){
            long timestamp = jsonParam.getJSONObject("content").getLong("timestamp");
            byte[] data = PushDataUtils.checkAndDecrypt(jsonParam.getJSONObject("content").getStr("data"),timestamp);
            log.info(ByteUtil.bytes2HexString(data));
        }
    }

    public JSONObject getJSONParam(HttpServletRequest request){
        JSONObject jsonParam = null;
        try {
            // 获取输入流
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = streamReader.readLine()) != null) {
                sb.append(line);
            }
            if(StrUtil.isNotBlank(sb.toString())){
                jsonParam = JSONUtil.parseObj(sb.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonParam;
    }



}

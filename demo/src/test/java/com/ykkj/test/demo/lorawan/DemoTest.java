package com.ykkj.test.demo.lorawan;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ykkj.demo.lierda.SendDataUtils;
import com.ykkj.demo.nplink.ByteUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName DemoTest.java
 * @Description TODO
 * @createTime 2021年11月18日
 */
@Slf4j
public class DemoTest {

    //static String url = "http://ykkj.yunkukeji.com:7345/uwb-data";
    //static String url = "http://127.0.0.1:8880/uwb-data";
    //static String url = "http://47.110.127.110:8080/api2/v1/lorawan/downlink";
    static String url = "http://127.0.0.1:8880/uwbData.jsp";
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true){

            try {
                DemoTest.sendLoraWan();
                ThreadUtil.sleep(1000*3);
            }catch (Exception e){
                e.printStackTrace();
                System.exit(1);
            }

        }
    }

    /**
     * {
     *   "data": "string",
     *   "devEui": "string",
     *   "fPort": 0,
     *   "modeEnum": "DEFAULT_MODE",
     *   "priority": true,
     *   "timestamp": 0,
     *   "useClassA": true
     * }
     */
    public static void sendLoraWan() throws Exception {
        JSONObject jsonObject = new JSONObject();
        byte[] data = ByteUtil.hexStringToBytes("FD 01 05 11 11 00 01 46 04 00 35 A5 EC 00 00 49");
        jsonObject.put("data",SendDataUtils.signAndEncrypt(data,System.currentTimeMillis()));
        jsonObject.put("devEui","DF4DB8E9A8A35F3B");
        jsonObject.put("fPort",1);
        jsonObject.put("modeEnum","DEFAULT_MODE");
        jsonObject.put("priority","true");
        jsonObject.put("timestamp",System.currentTimeMillis());
        jsonObject.put("useClassA",true);
        String data1 = "{\"serverTime\":1637294292172,\"type\":7001,\"projectId\":\"6196044f352df3000156fa11\",\"content\":{\"data\":\"FF EE 51 02 CA 00 05 30 DA 36 29 04 00 00 00 00 24 02 A2 64 00 00 00 25 02 B8 64 00 00 00 23 02 A1 64 00 00 00 20 02 A8 64 06 39 EE FF\",\"fPort\":10,\"devEui\":\"009569000001628A\",\"group\":\"UWB测试\",\"timestamp\":1637294292185}}";
        log.info(data1);
        String message = HttpUtil.post(url,data1);
        log.info(message.trim());
    }

    public static void testSendWan(){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type",7001);

        JSONObject contentJson = JSONUtil.createObj();
        contentJson.put("devEui","009569000001628A");
        contentJson.put("data","FD 01 05 11 11 00 01 46 04 00 35 A5 EC 00 00 49");
        contentJson.put("timestamp",System.currentTimeMillis());
        contentJson.put("fPort",1);
        jsonObject.put("content",contentJson);
        jsonObject.put("projectId","6196044f352df3000156fa11");
        jsonObject.put("serverTime", System.currentTimeMillis());

        HttpUtil.post(url,jsonObject.toString());

        log.info("ok");
    }
}

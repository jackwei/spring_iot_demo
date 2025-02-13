package com.ykkj.test.demo.lorawan;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName HexTest.java
 * @Description TODO
 * @createTime 2021年12月02日
 */
@Slf4j
public class HexTest {


    public static void main(String[] args) {
        String data1 = "FF EE 51 02 CA 00 05 30 DA 36 29 04 00 00 00 00 24 02 A2 64 00 00 00 25 02 B8 64 00 00 00 23 02 A1 64 00 00 00 20 02 A8 64 06 39 EE FF";
        String data = "05 30 DA 36 29 04 00 00 00 00 24 02 A2 64 00 00 00 25 02 B8 64 00 00 00 23 02 A1 64 00 00 00 20 02 A8 64";
        //06 39：校验和 （消息类型（05） 到 校验和前的 数据内容 进行校验和计算，取最后两个字节）
        log.info(makeChecksum(data.replaceAll(" ","")));
    }

    public static String makeChecksum(String data) {
        if (data == null || data.equals("")) {
            return "";
        }
        int total = 0;
        int len = data.length();
        int num = 0;
        while (num < len) {
            String s = data.substring(num, num + 2);
            System.out.println(s);
            total += Integer.parseInt(s, 16);
            num = num + 2;
        }
        /**
         * 用256求余最大是255，即16进制的FF
         */

        log.info(Integer.toHexString(total));

        int mod = total % 256;
        String hex = Integer.toHexString(mod);
        len = hex.length();
        // 如果不够校验位的长度，补0,这里用的是两位校验
        if (len < 2) {
            hex = "0" + hex;
        }
        return hex;
    }

    /***
     * 计算校验和
     * @param data
     * @return
     */
    public static String makeChecksum1(String data) {
        if (data == null || data.equals("")) {
            return "";
        }
        int total = 0;
        int len = data.length();
        int num = 0;
        while (num < len) {
            String s = data.substring(num, num + 2);
            // System.out.println(s);
            total += Integer.parseInt(s, 16);
            num = num + 2;
        }
        /**
         * 用256求余最大是255，即16进制的FF
         */
        int mod = total % 256;
        String hex = Integer.toHexString(mod);
        len = hex.length();
        // 如果不够校验位的长度，补0,这里用的是两位校验
        if (len < 2) {
            hex = "0" + hex;
        }
        return hex;
    }

}

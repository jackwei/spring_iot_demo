package com.ykkj.demo.lierda;

import java.util.Arrays;
import java.util.Base64;

public final class PushDataUtils {

    private static final boolean ENCRYPTION = true; // 指定应用接口是否加密（默认为true）

    private static final int SIGNATURE_LENGTH = 32; // 数字签名（字节数组C）占用字节数

    private static final String SIGN_TOKEN = "F867DBC5B14B21C9"; // 应用接口的signToken

    private static final String SECRET_KEY = "E7506FC18E70F277794765767EE6F3C4"; // 应用接口的secretKey

    private PushDataUtils() {
    }

    /**
     * @param data      对应data字段
     * @param timestamp 对应timestamp字段
     * @return
     */
    public static byte[] checkAndDecrypt(String data, Long timestamp) throws Exception {

        // 1. 校验消息时间戳, 一定程度防止重放攻击
        //
        // 2. Base64解码
        //
        // 3. 如项目加密属性为true, 解密之
        //
        // 4. 校验签名, 若成功, 才使用

        if (System.currentTimeMillis() > timestamp + 30000) { // 时间差不能超过30s
            return null;
        }

        byte[] plaintext = Base64.getDecoder().decode(data);

        if (ENCRYPTION) {
            plaintext = AESUtils.decryptECBPKCS5Padding(SECRET_KEY, plaintext);
        }

        if (plaintext.length <= SIGNATURE_LENGTH) {
            return null;
        }

        byte[] signature = Arrays.copyOfRange(plaintext, 0, SIGNATURE_LENGTH);
        byte[] payload = Arrays.copyOfRange(plaintext, SIGNATURE_LENGTH, plaintext.length);

        byte[] recomputed = HmacSHA256Utils.digest(SIGN_TOKEN, ByteUtils.concat(payload,
                ByteUtils.longToBytesBE(timestamp)));

        if (!Arrays.equals(signature, recomputed)) {
            return null;
        }
        return payload;
    }

}
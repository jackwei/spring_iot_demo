package com.ykkj.test.demo.ThreadLocal;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName Command.java
 * @Description TODO
 * @createTime 2023年02月10日
 */
public class Command {

    static ThreadLocal<String> name = new ThreadLocal<>();
    static InheritableThreadLocal<String> key = new InheritableThreadLocal<>();
    static TransmittableThreadLocal<String> codec = new TransmittableThreadLocal<>();

    public static String getName() {
        return name.get();
    }

    public static String getKey() {
        return key.get();
    }

    public static String getCodec() {
        return codec.get();
    }

    public static void setName(String name) {
        Command.name.set(name);
    }

    public static void setKey(String key) {
        Command.key.set(key);
    }

    public static void setCodec(String codec) {
        Command.codec.set(codec);
    }

}


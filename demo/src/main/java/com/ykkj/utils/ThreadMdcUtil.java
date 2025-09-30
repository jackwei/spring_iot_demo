//package com.ykkj.utils;
//
//import cn.hutool.core.util.IdUtil;
//import com.ykkj.constant.Constants;
//import org.slf4j.MDC;
//
//import java.util.Map;
//
//public class ThreadMdcUtil {
//    // 获取唯一性标识
//    public static String generateTraceId() {
//        return IdUtil.randomUUID();
//    }
//
//    public static void setTraceIdIfAbsent() {
//        if (MDC.get(Constants.TRACE_ID) == null) {
//            MDC.put(Constants.TRACE_ID, generateTraceId());
//        }
//    }
//
//    /**
//     * 用于父线程向线程池中提交任务时，将自身MDC中的数据复制给子线程
//     *
//     * @param runnable  要执行的线程
//     * @param context   父线程的mdc
//     * @return          链路id传递后的任务
//     */
//    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
//        return () -> {
//            if (context == null) {
//                MDC.clear();
//            } else {
//                MDC.setContextMap(context);
//            }
//            setTraceIdIfAbsent();
//            try {
//                runnable.run();
//            } finally {
//                MDC.clear();
//            }
//        };
//    }
//}

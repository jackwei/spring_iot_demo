package com.ykkj.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName RemoteService.java
 * @Description TODO
 * @createTime 2023年01月13日
 */
@FeignClient(name = "hqkh",url = "http://192.168.1.198:8801/hqkh")
public interface RemoteService {

    @GetMapping("/api/service/sys/taskHandle")
    public String taskHandle();
}

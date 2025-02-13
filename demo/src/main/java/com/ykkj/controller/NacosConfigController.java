package com.ykkj.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName NacosConfigController.java
 * @Description TODO
 * @createTime 2023年01月10日
 */
@Controller
@RequestMapping("config")
public class NacosConfigController {

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @NacosInjected
    private NamingService namingService;

    @RequestMapping(value = "/getName", method = RequestMethod.GET)
    @ResponseBody
    public List<Instance> getName(@RequestParam String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public boolean get() {
        return useLocalCache;
    }
}

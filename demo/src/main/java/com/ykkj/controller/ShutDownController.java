package com.ykkj.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName ShutDownController.java
 * @Description TODO
 * @createTime 2023年03月22日
 */

@RestController
public class ShutDownController implements ApplicationContextAware {

    ApplicationContext applicationContext;

    //@PostMapping("/shutDownContext")

    @RequestMapping(value = "/shutDownContext",method = {RequestMethod.GET,RequestMethod.POST})
    public String shutDownContext() {
        ConfigurableApplicationContext ctx = (ConfigurableApplicationContext) applicationContext;
        ctx.close();
        return "context is shutdown";
    }

    @GetMapping("/status")
    public String getIndex() {
        return "OK";
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

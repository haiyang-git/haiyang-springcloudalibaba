package com.haiyang.sca.openapi.controller;

import com.haiyang.sca.api.IHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author wanghaiyang
 * @version 1.0.0
 * @className HelloController.java
 * @description 接口controller
 * @createTime 2020年12月16日 14:40:00
 */
@RestController
public class HelloController {
    @Reference(version = "1.0.0")
    private IHelloService helloService;

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return helloService.say(name);
    }
}

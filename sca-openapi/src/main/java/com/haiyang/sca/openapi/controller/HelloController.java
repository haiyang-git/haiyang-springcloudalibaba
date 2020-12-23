package com.haiyang.sca.openapi.controller;

import com.haiyang.sca.api.IHelloService;
import com.haiyang.sca.openapi.config.NacosConfig;
import com.haiyang.sca.openapi.vo.UserVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope
public class HelloController {
     @Value("${sca.name}")
      private String userName;
    @Autowired
    NacosConfig nacosConfig;
    @DubboReference(version = "1.0.0")
    private IHelloService helloService;

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return helloService.say(name);
    }

    @GetMapping("/getName")
    public String getName() {
        return userName;
    }
}

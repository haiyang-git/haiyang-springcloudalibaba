package com.haiyang.sca.provider.service.hello;

import com.haiyang.sca.api.IHelloService;
import org.apache.dubbo.config.annotation.DubboService;


/**
 * @author wanghaiyang
 * @version 1.0.0
 * @className HelloServiceImpl.java
 * @description 定义接口api 实现
 * @createTime 2020年12月16日 14:40:00
 */
@DubboService(version = "1.0.0")
public class HelloServiceImpl implements IHelloService {
    @Override
    public String say(String name) {
        return "hello " + name;
    }
}

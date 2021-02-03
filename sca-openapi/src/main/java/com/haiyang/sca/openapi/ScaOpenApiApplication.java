package com.haiyang.sca.openapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wanghaiyang
 * @version 1.0.0
 * @className ScaOpenApiApplication.java
 * @description 启动类
 * @createTime 2020年12月16日 14:40:00
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ScaOpenApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScaOpenApiApplication.class);
        System.out.println("openapi server start");
    }

}

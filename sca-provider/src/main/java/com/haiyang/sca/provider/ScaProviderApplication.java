package com.haiyang.sca.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * @author wanghaiyang
 * @version 1.0.0
 * @className ScaProviderApplication.java
 * @description 启动类
 * @createTime 2020年12月16日 14:40:00
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.haiyang.sca.provider.mapper")
public class ScaProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScaProviderApplication.class);
        System.out.println("provider server start");
    }
}

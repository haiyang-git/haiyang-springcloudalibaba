package com.haiyang.sca.openapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author wanghaiyang
 * @version 1.0.0
 * @className CheckController.java
 * @description 健康检查
 * @createTime 2020年12月16日 14:40:00
 */
@RestController
public class CheckController {
    @GetMapping("/check")
    public String check() {
        return "success";
    }
}

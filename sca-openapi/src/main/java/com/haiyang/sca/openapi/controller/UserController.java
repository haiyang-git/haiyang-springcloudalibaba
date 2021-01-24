package com.haiyang.sca.openapi.controller;

import com.haiyang.sca.api.IUserService;
import com.haiyang.sca.openapi.base.R;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @DubboReference(version = "1.0.0")
    private IUserService userService;

    @GetMapping("/getUsers")
    public R getUsers() {
        return new R(userService.getUsers());
    }


}

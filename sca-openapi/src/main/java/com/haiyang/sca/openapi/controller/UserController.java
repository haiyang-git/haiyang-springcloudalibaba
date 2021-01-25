package com.haiyang.sca.openapi.controller;

import com.haiyang.sca.api.IUserService;
import com.haiyang.sca.dto.UserDTO;
import com.haiyang.sca.openapi.mapstruct.UserDTO2VOMapper;
import com.haiyang.sca.openapi.vo.R;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @DubboReference(version = "1.0.0")
    private IUserService userService;

    @GetMapping("/getUsers")
    public Object getUsers() {
        List<UserDTO> users = userService.getUsers();
        return R.success(UserDTO2VOMapper.INSTANCE.toVO(users));
    }


}

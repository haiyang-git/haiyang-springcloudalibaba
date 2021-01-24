package com.haiyang.sca.provider.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haiyang.sca.api.IUserService;
import com.haiyang.sca.dto.UserDTO;
import com.haiyang.sca.provider.mapper.UserMapper;
import com.haiyang.sca.provider.mapstruct.UserModel2DTOMapper;
import com.haiyang.sca.provider.model.User;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService(version = "1.0.0")
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<UserDTO> getUsers() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> users = userMapper.selectList(queryWrapper);
        return UserModel2DTOMapper.INSTANCE.toDTO(users);
    }
}

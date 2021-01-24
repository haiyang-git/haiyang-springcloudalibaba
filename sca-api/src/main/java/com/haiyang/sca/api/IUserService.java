package com.haiyang.sca.api;

import com.haiyang.sca.dto.UserDTO;

import java.util.List;

public interface IUserService {

    List<UserDTO> getUsers();
}

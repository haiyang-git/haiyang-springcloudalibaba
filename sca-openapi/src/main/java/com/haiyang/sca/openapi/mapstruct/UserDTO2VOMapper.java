package com.haiyang.sca.openapi.mapstruct;

import com.haiyang.sca.dto.UserDTO;
import com.haiyang.sca.openapi.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wanghaiyang
 * @version 1.0.0
 * @className UserDTO2VO.java
 * @description
 * @createTime 2021年01月25日 16:50:00
 */
@Mapper(componentModel = "spring")
public interface UserDTO2VOMapper extends BaseDTO2VOMapper<UserDTO, UserVO> {
    UserDTO2VOMapper INSTANCE = Mappers.getMapper(UserDTO2VOMapper.class);
}

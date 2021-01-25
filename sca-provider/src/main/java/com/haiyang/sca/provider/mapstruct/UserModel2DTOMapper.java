package com.haiyang.sca.provider.mapstruct;

import com.haiyang.sca.dto.UserDTO;
import com.haiyang.sca.provider.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserModel2DTOMapper {
    UserModel2DTOMapper INSTANCE = Mappers.getMapper(UserModel2DTOMapper.class);

    List<UserDTO> toDTO(List<User> user);
}

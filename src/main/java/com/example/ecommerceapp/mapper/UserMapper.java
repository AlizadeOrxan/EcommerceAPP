package com.example.ecommerceapp.mapper;

import com.example.ecommerceapp.dto.UserDto;
import com.example.ecommerceapp.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    List<UserDto> toDtoList(List<User> users);


}

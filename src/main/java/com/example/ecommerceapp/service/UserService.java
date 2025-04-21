package com.example.ecommerceapp.service;

import com.example.ecommerceapp.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    List<UserDto> getAll();
}

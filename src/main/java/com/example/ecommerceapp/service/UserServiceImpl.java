package com.example.ecommerceapp.service;

import com.example.ecommerceapp.dto.UserDto;
import com.example.ecommerceapp.entity.User;
import com.example.ecommerceapp.mapper.UserMapper;
import com.example.ecommerceapp.repository.UserRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

   private static final String UserCache = "UserCache";
   private final UserRepository userRepository;
   private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @CachePut(value = UserCache, key = "#result.id()")
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userRepository.findById(userDto.id()).orElseThrow(
                () -> new RuntimeException("User not found with id: " + userDto.id())
        );

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }


    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }
}

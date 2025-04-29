package com.example.ecommerceapp.service;

import com.example.ecommerceapp.dto.ProductDto;
import com.example.ecommerceapp.dto.UserDto;
import com.example.ecommerceapp.entity.User;
import com.example.ecommerceapp.exception.UserNotFoundException;
import com.example.ecommerceapp.mapper.ProductMapper;
import com.example.ecommerceapp.mapper.UserMapper;
import com.example.ecommerceapp.repository.UserRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImpl implements UserService {

   private static final String UserCache = "user";
   private final UserRepository userRepository;
   private final ProductMapper productMapper;

    public UserServiceImpl(UserRepository userRepository, ProductMapper productMapper) {
        this.userRepository = userRepository;
        this.productMapper = productMapper;
    }

    @CachePut(value = UserCache, key = "#result.id()")
    @Override
    public UserDto createUser(UserDto userDto) {
       if (userRepository.existsByUsername(userDto.getUsername())) {
           throw new IllegalArgumentException("Username already exists");
       }

       if (userRepository.existsByEmail(userDto.getEmail())) {
           throw new IllegalArgumentException("Email already exists");
       }
       User user = new User(userDto.getUsername(),userDto.getEmail(), LocalDateTime.now());
       user = userRepository.save(user);

       return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt());
    }


    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt()))
                .collect(toList());
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(
                () -> new UserNotFoundException("User not found with id: " + id));

        List<ProductDto> products = user.getProducts().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());


        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt(), products);
    }
}

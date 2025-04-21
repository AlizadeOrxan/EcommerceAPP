package com.example.ecommerceapp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;


import java.time.LocalDateTime;


public record UserDto(Long id , @NotBlank String username, @Valid String email, LocalDateTime createdAt) {

}

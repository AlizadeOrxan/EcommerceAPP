package com.example.bffecommerce.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    Long id;

    Long userId;

    String name;

    String description;

    BigDecimal price;
}

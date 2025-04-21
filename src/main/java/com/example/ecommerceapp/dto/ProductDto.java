package com.example.ecommerceapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {

    Long id;

    Long userId;

    String name;

    String description;

    BigDecimal price;


}

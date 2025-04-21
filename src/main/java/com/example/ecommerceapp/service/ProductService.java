package com.example.ecommerceapp.service;

import com.example.ecommerceapp.dto.ProductDto;

import java.util.List;

public interface ProductService {


    ProductDto createProduct(ProductDto productDto);

    ProductDto getById(Long id);

    List<ProductDto> getAll();

    String updateProduct(ProductDto productDto);

    String deleteProductById(Long id);
}

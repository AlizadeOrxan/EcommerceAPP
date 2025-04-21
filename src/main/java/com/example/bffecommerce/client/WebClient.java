package com.example.bffecommerce.client;

import com.example.bffecommerce.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "E-commerce-APP" , url = "http://localhost:8989/api/v1/products")
public interface WebClient {

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/add")
    ProductDto createProduct(ProductDto productDto);

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/edit")
    String productUpdate(ProductDto productDto);

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
     List<ProductDto> getAll();

    @GetMapping("/{productId}")
    @ResponseStatus(value = HttpStatus.OK)
    ProductDto getById(Long id);

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.OK)
    String deleteProductById(Long id);


}

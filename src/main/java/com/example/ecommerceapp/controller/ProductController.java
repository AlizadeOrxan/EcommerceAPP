package com.example.ecommerceapp.controller;

import com.example.ecommerceapp.dto.ProductDto;
import com.example.ecommerceapp.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService; //dep inj

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @PutMapping("/products/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public String productUpdate(ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @GetMapping("/products")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @GetMapping("/products/{productId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ProductDto getById(@PathVariable Long productId){
        return productService.getById(productId);
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
        return "Product deleted successfully";
    }


}

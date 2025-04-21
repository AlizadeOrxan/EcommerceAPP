package com.example.bffecommerce.controller;

import com.example.bffecommerce.dto.ProductDto;
import com.example.bffecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bff")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/add")
    public ProductDto createProduct(ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/edit")
    public String productUpdate(ProductDto productDto) {
        return productService.productUpdate(productDto);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{productId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ProductDto getById(Long id){
        return productService.getById(id);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteProductById(Long id){
        productService.deleteProductById(id);
        return "Product deleted successfully";
    }
}

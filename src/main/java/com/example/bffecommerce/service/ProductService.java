package com.example.bffecommerce.service;

import com.example.bffecommerce.client.WebClient;
import com.example.bffecommerce.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private WebClient webClient;

    public ProductService(WebClient webClient) {
        this.webClient = webClient;
    }

    public ProductDto createProduct(ProductDto productDto) {
        return webClient.createProduct(productDto);
    }

    public List<ProductDto> getAll() {
        return webClient.getAll();
    }

    public ProductDto getById(Long id) {
        return webClient.getById(id);
    }

    public String productUpdate(ProductDto productDto) {
        webClient.productUpdate(productDto);

        return "product updated";
    }

     public  String deleteProductById(Long id) {
        webClient.deleteProductById(id);

        return "product deleted";
    }


}



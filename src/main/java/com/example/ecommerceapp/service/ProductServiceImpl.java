package com.example.ecommerceapp.service;

import com.example.ecommerceapp.dto.ProductDto;
import com.example.ecommerceapp.entity.Product;
import com.example.ecommerceapp.entity.User;
import com.example.ecommerceapp.exception.ProductNotFoundException;
import com.example.ecommerceapp.exception.UserNotFoundException;
import com.example.ecommerceapp.mapper.ProductMapper;
import com.example.ecommerceapp.repository.ProductRepository;
import com.example.ecommerceapp.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final static String PRODUCT_CACHE = "products";
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.userRepository = userRepository;
    }

    @CachePut(value = PRODUCT_CACHE, key = "#result.getId()")
    public ProductDto createProduct(ProductDto productDto) {
        User user = userRepository.findById(productDto.getUserId()).orElseThrow(() ->
                new UserNotFoundException("User not found with id: " + productDto.getUserId()));

        Product product = productMapper.toEntity(productDto);

        product.setUser(user);
        product = productRepository.save(product);

        return productMapper.toDto(product);
    }

    @Cacheable(value = PRODUCT_CACHE, key = "#id")
    public ProductDto getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        return productMapper.toDto(product);
    }

    @Cacheable(value = PRODUCT_CACHE, key = "'all'")
    public List<ProductDto> getAll() {
        List<Product> all = productRepository.findAll();
        return productMapper.toDtoList(all);
    }

    @CachePut(value = PRODUCT_CACHE, key = "#result.getId()")
    public String updateProduct(ProductDto productDto) {
        Long id = productDto.getId();

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("this product not exists"));

        User user = userRepository.findById(productDto.getUserId())
                        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + productDto.getUserId()));

        productMapper.mapDtoToEntity(productDto, product);
        product.setUser(user);
        productRepository.save(product);

        return "product updated successfully with id: " + id;
    }

    @CacheEvict(value =  PRODUCT_CACHE, key = "#id")
    public String deleteProductById(Long id) {
        productRepository.deleteById(id);
        return "SuccessFully Has deleted";
    }


}

package com.example.ecommerceapp.service;

import com.example.ecommerceapp.dto.ProductDto;
import com.example.ecommerceapp.entity.Product;
import com.example.ecommerceapp.entity.User;
import com.example.ecommerceapp.mapper.ProductMapper;
import com.example.ecommerceapp.repository.ProductRepository;
import com.example.ecommerceapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.math.BigDecimal;
import java.util.Optional;

import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductMapper productMapper;


    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @Test
    void createProduct_shouldProductAndReturnProductDto() {
        ProductDto inputDto = new ProductDto();
        inputDto.setUserId(1L);
        inputDto.setName("Test Data");
        inputDto.setPrice(BigDecimal.valueOf(10));

        Product product = new Product();
        product.setId(1L);
        product.setName("Test Data");
        product.setDescription("Test Description");
        product.setPrice(BigDecimal.valueOf(10));

        Product savedEntity = new Product();
        savedEntity.setId(1L);
        savedEntity.setName("Test Product");
        savedEntity.setDescription("Test Description");
        savedEntity.setPrice(BigDecimal.valueOf(10));

        ProductDto expectedDto = new ProductDto();
        expectedDto.setId(1L);
        expectedDto.setName("Test Data");
        expectedDto.setPrice(BigDecimal.valueOf(10));

        User user = new User();
        user.setId(1L);
        user.setUsername("User name 1");
        user.setEmail("test@mail.ru");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(productMapper.toEntity(inputDto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(savedEntity);
        when(productMapper.toDto(savedEntity)).thenReturn(expectedDto);

        ProductDto actualDto = productServiceImpl.createProduct(inputDto);

        assertEquals(expectedDto.getId(), actualDto.getId());
        assertEquals(expectedDto.getName(), actualDto.getName());
        assertEquals(expectedDto.getDescription(), actualDto.getDescription());
        assertEquals(expectedDto.getPrice(), actualDto.getPrice());

        verify(userRepository).findById(1L);
        verify(productRepository).save(product);
        verify(productMapper).toEntity(inputDto);
        verify(productMapper).toDto(savedEntity);
    }


}

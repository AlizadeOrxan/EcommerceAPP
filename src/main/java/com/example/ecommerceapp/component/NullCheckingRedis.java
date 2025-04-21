package com.example.ecommerceapp.component;

import com.example.ecommerceapp.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("checking")
public class NullCheckingRedis {

    public boolean takeNullChecking(ProductDto productDto) {
        return Arrays.stream(productDto.getClass()
                            .getDeclaredFields())
                    .peek(take -> take.setAccessible(true))
                    .anyMatch(t -> {
                        try {
                            return t.get(productDto) == null;
                        } catch (IllegalAccessException e) {
                            return true;
                        }
                    });
        }
}
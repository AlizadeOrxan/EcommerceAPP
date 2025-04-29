package com.example.ecommerceapp.mapper;

import com.example.ecommerceapp.dto.ProductDto;
import com.example.ecommerceapp.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    Product toEntity(ProductDto productDto);


    ProductDto toDto(Product product);

    List<ProductDto> toDtoList(List<Product> productList);


    void mapDtoToEntity(ProductDto productDto, @MappingTarget Product product);

}

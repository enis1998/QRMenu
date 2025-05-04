package com.qrmenu.mapper.Product;


import com.qrmenu.dto.Product.requests.ProductRequestDto;
import com.qrmenu.dto.Product.responses.ProductResponseDto;
import com.qrmenu.entity.Product;

public interface ProductMapper {
    ProductResponseDto toDto(Product entity);
    Product toEntity(ProductRequestDto dto);
    Product updateEntityFromDto(ProductRequestDto dto, Product entity);
}

package com.qrmenu.mapper.Product;

import com.qrmenu.dto.Product.requests.ProductRequestDto;
import com.qrmenu.dto.Product.responses.ProductResponseDto;
import com.qrmenu.entity.Product;
import org.springframework.stereotype.Component;


@Component
public class ProductMapperImpl implements ProductMapper{
    @Override
    public ProductResponseDto toDto(Product product) {
        if (product == null) return null;
        ProductResponseDto dto = new ProductResponseDto();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStatus(product.getStatus());
        dto.setImage(product.getImage());
        dto.setImageContentType(product.getImageContentType());
        dto.setCategory(product.getCategory());

        return dto;
    }

    @Override
    public Product toEntity(ProductRequestDto dto) {
        if (dto == null) return null;
        Product product = new Product();

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStatus(product.getStatus());
        product.setImage(dto.getImage());
        product.setImageContentType(dto.getImageContentType());
        product.setCategory(dto.getCategory());

        return product;
    }

    @Override
    public Product updateEntityFromDto(ProductRequestDto dto, Product product) {
        if (dto == null || product == null) return null;

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStatus(product.getStatus());
        product.setImage(dto.getImage());
        product.setImageContentType(dto.getImageContentType());
        product.setCategory(dto.getCategory());

        return product;
    }
}

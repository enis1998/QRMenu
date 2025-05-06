package com.qrmenu.mapper.Product;

import com.qrmenu.dto.Product.requests.ProductRequestDto;
import com.qrmenu.dto.Product.responses.ProductResponseDto;
import com.qrmenu.entity.Category;
import com.qrmenu.entity.Product;
import com.qrmenu.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class ProductMapperImpl implements ProductMapper{

    private CategoryRepository categoryRepository;

    public ProductMapperImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

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
        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getName());
        }


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

        if (dto.getCategoryId() != null) {
            Optional<Category> categoryOpt = categoryRepository.findById(dto.getCategoryId());
            categoryOpt.ifPresent(product::setCategory);
        }


        return product;
    }

    @Override
    public Product updateEntityFromDto(ProductRequestDto dto, Product product) {
        if (dto == null || product == null) return null;

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStatus(product.getStatus());

        if (dto.getImage() != null && dto.getImage().length > 0) {
            product.setImage(dto.getImage());
            product.setImageContentType(dto.getImageContentType());
        }

        if (dto.getCategoryId() != null) {
            Optional<Category> categoryOpt = categoryRepository.findById(dto.getCategoryId());
            categoryOpt.ifPresent(product::setCategory);
        } else {
            product.setCategory(null);
        }


        return product;
    }
}

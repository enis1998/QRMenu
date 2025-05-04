package com.qrmenu.controller.Admin.Contract.Impl;

import com.qrmenu.controller.Admin.Contract.AdminContract;
import com.qrmenu.dto.Product.requests.ProductRequestDto;
import com.qrmenu.dto.Product.responses.ProductResponseDto;
import com.qrmenu.entity.Category;
import com.qrmenu.entity.Product;
import com.qrmenu.mapper.Product.ProductMapper;
import com.qrmenu.service.CategoryService;
import com.qrmenu.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Transactional
public class AdminContractImpl implements AdminContract {
    private final CategoryService categoryService;
    private final ProductService productService;
    private final ProductMapper productMapper;

    public AdminContractImpl(CategoryService categoryService, ProductService productService, ProductMapper productMapper) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @Override
    public Map<UUID, List<ProductResponseDto>> findByCategory() {
        List<Category> categories = categoryService.findAll();

        Map<UUID,List<ProductResponseDto>> productsByCategoryMap = categories.stream()
                .collect(Collectors.toMap(
                        Category::getId,
                        cat -> productService.findByCategory(cat).stream()
                                .map(productMapper::toDto)
                                .toList()
                ));
        return productsByCategoryMap;
    }

    @Override
    public ProductResponseDto findByProduct(UUID id) {
        Product product = productService.findById(id);
        return productMapper.toDto(product);
    }

    @Override
    public void saveProduct(ProductRequestDto productRequestDto) {
        Product product = productMapper.toEntity(productRequestDto);
        productService.save(product);
    }

    @Override
    public void updateProduct(UUID id, ProductRequestDto productRequestDto) {
        Product product = productService.findById(id);
        product = productMapper.updateEntityFromDto(productRequestDto, product);
        productService.save(product);
    }
}

package com.qrmenu.controller.Admin.Contract;

import com.qrmenu.dto.Product.requests.ProductRequestDto;
import com.qrmenu.dto.Product.responses.ProductResponseDto;
import com.qrmenu.entity.Product;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface AdminContract {
    Map<UUID,List<ProductResponseDto>> findByCategory();
    ProductResponseDto findByProduct(UUID id);
    void saveProduct(ProductRequestDto productRequestDto);
    void updateProduct(UUID id, ProductRequestDto productRequestDto);

}

package com.qrmenu.service;


import com.qrmenu.entity.Category;
import com.qrmenu.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Long countAll();
    List<Product> findAll();
    Product findById(UUID id);
    Product save(Product product);
    void delete(UUID id);
    List<Product> findByCategory(Category category);
    List<Product> findActiveByCategory(Category category);
}

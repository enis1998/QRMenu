package com.qrmenu.service;


import com.qrmenu.entity.Category;
import com.qrmenu.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
    void delete(Long id);
    List<Product> findActiveByCategory(Category category);
}

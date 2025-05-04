package com.qrmenu.service;


import com.qrmenu.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> findAllByStatus();
    List<Category> findAll();
    Category findById(UUID id);
    Category save(Category category);
    void delete(UUID id);
}

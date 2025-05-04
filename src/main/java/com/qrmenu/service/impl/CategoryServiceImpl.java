package com.qrmenu.service.impl;

import com.qrmenu.entity.Category;
import com.qrmenu.enums.Status;
import com.qrmenu.repository.CategoryRepository;
import com.qrmenu.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllByStatus() {
        return categoryRepository.findAllByStatus(Status.ACTIVE);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(UUID id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(UUID id) {
        categoryRepository.deleteById(id);
    }
}

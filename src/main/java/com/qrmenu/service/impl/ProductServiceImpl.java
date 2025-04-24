package com.qrmenu.service.impl;

import com.qrmenu.entity.Category;
import com.qrmenu.entity.Product;
import com.qrmenu.repository.CategoryRepository;
import com.qrmenu.repository.ProductRepository;
import com.qrmenu.service.CategoryService;
import com.qrmenu.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository catRepo;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository catRepo) {
        this.productRepository = productRepository;
        this.catRepo = catRepo;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findActiveByCategory(Category category) {
        return productRepository.findByCategoryAndActiveTrue(category);
    }
    public void assignCategory(Long productId, Long categoryId) {
        Product p = findById(productId);
        Category c = catRepo.findById(categoryId).orElse(null);
        if (p!=null && c!=null) {
            p.setCategory(c);
            productRepository.save(p);
        }
    }
}

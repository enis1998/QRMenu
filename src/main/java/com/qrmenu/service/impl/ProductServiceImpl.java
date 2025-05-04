package com.qrmenu.service.impl;

import com.qrmenu.entity.Category;
import com.qrmenu.entity.Product;
import com.qrmenu.enums.Status;
import com.qrmenu.repository.CategoryRepository;
import com.qrmenu.repository.ProductRepository;
import com.qrmenu.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository catRepo;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository catRepo) {
        this.productRepository = productRepository;
        this.catRepo = catRepo;
    }

    @Override
    public Long countAll() {
        return productRepository.count();
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(UUID id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(UUID id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }
    @Override
    public List<Product> findActiveByCategory(Category category) {
        return productRepository.findByCategoryAndStatus(category, Status.ACTIVE);
    }
    public void assignCategory(UUID productId, UUID categoryId) {
        Product p = findById(productId);
        Category c = catRepo.findById(categoryId).orElse(null);
        if (p!=null && c!=null) {
            p.setCategory(c);
            productRepository.save(p);
        }
    }
}

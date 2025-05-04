package com.qrmenu.repository;


import com.qrmenu.entity.Category;
import com.qrmenu.entity.Product;
import com.qrmenu.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByCategoryAndStatus(Category category, Status status);
    List<Product> findByCategory(Category category);
}

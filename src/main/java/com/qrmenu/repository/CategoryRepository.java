package com.qrmenu.repository;


import com.qrmenu.entity.Category;
import com.qrmenu.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findAllByStatus(Status status);
}

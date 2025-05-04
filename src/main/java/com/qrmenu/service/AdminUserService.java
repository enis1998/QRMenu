package com.qrmenu.service;

import com.qrmenu.entity.AdminUser;

import java.util.List;
import java.util.UUID;

public interface AdminUserService {
    Long countAll();
    List<AdminUser> findAll();
    AdminUser findById(UUID id);
    AdminUser findByUsername(String username);
    AdminUser save(AdminUser user);
    void delete(UUID id);
}

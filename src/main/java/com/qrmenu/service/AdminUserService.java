package com.qrmenu.service;

import com.qrmenu.entity.AdminUser;

import java.util.List;

public interface AdminUserService {
    List<AdminUser> findAll();

    AdminUser findById(Long id);
    AdminUser findByUsername(String username);
    AdminUser save(AdminUser user);
    void delete(Long id);
}

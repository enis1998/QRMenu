package com.qrmenu.service.impl;

import com.qrmenu.entity.AdminUser;
import com.qrmenu.mapper.User.AdminUserMapper;
import com.qrmenu.repository.AdminUserRepository;
import com.qrmenu.service.AdminUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminUserRepository repo;
    private final AdminUserMapper mapper;

    public AdminUserServiceImpl(AdminUserRepository repo, AdminUserMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public Long countAll() {
        return repo.count();
    }

    @Override
    public List<AdminUser> findAll() {
        return repo.findAll();
    }

    @Override
    public AdminUser findById(UUID id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public AdminUser findByUsername(String email) {
        return repo.findByEmail(email).orElse(null);
    }

    @Override
    public AdminUser save(AdminUser user) {
        return repo.save(user);
    }

    @Override
    public void delete(UUID id) {
        repo.deleteById(id);
    }
}

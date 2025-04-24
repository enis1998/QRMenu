package com.qrmenu.controller.contract.impl;

import com.qrmenu.controller.contract.AdminUserContract;
import com.qrmenu.dto.AdminUserDto;
import com.qrmenu.entity.AdminUser;
import com.qrmenu.mapper.AdminUserMapper;
import com.qrmenu.service.AdminUserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
public class AdminUserContractImpl implements AdminUserContract {

    private final AdminUserService userService;
    private final AdminUserMapper mapper;
    private final PasswordEncoder encoder;

    public AdminUserContractImpl(AdminUserService userService,
                                 AdminUserMapper mapper,
                                 PasswordEncoder encoder) {
        this.userService = userService;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @Override
    public List<AdminUserDto> getAllUsers() {
        return userService.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public AdminUserDto getUserById(Long id) {
        return mapper.toDto(userService.findById(id));
    }

    @Override
    public AdminUserDto createUser(AdminUserDto dto) {
        // şifre hash’leme
        dto.setPassword(encoder.encode(dto.getPassword()));
        AdminUser saved = userService.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Override
    public AdminUserDto updateUser(Long id, AdminUserDto dto) {
        AdminUser existing = userService.findById(id);
        if (existing == null) {
            return null;
        }
        // parola boş gelmişse orijinal hash kalır
        if (!dto.getPassword().isBlank()) {
            dto.setPassword(encoder.encode(dto.getPassword()));
        } else {
            dto.setPassword(existing.getPassword());
        }
        mapper.updateEntityFromDto(dto, existing);
        AdminUser updated = userService.save(existing);
        return mapper.toDto(updated);
    }

    @Override
    public void deleteUser(Long id) {
        userService.delete(id);
    }
}

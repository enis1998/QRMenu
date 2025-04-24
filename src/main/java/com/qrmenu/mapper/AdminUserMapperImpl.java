package com.qrmenu.mapper;

import com.qrmenu.dto.AdminUserDto;
import com.qrmenu.entity.AdminUser;
import org.springframework.stereotype.Component;

@Component
public class AdminUserMapperImpl implements AdminUserMapper {

    @Override
    public AdminUserDto toDto(AdminUser entity) {
        if (entity == null) return null;
        AdminUserDto dto = new AdminUserDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());
        dto.setEnabled(entity.isEnabled());
        return dto;
    }

    @Override
    public AdminUser toEntity(AdminUserDto dto) {
        if (dto == null) return null;
        AdminUser entity = new AdminUser();
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        entity.setEnabled(dto.isEnabled());
        return entity;
    }

    @Override
    public void updateEntityFromDto(AdminUserDto dto, AdminUser entity) {
        if (dto == null || entity == null) return;
        entity.setEmail(dto.getEmail());
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            entity.setPassword(dto.getPassword());
        }
        entity.setRole(dto.getRole());
        entity.setEnabled(dto.isEnabled());
    }
}

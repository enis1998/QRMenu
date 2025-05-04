package com.qrmenu.mapper.User;

import com.qrmenu.dto.User.requests.AdminUserRequestDto;
import com.qrmenu.dto.User.responses.AdminUserResponseDto;
import com.qrmenu.entity.AdminUser;
import org.springframework.stereotype.Component;

@Component
public class AdminUserMapperImpl implements AdminUserMapper {

    @Override
    public AdminUserResponseDto toDto(AdminUser entity) {
        if (entity == null) return null;
        AdminUserResponseDto dto = new AdminUserResponseDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());
        dto.setEnabled(entity.isEnabled());
        return dto;
    }

    @Override
    public AdminUser toEntity(AdminUserRequestDto dto) {
        if (dto == null) return null;
        AdminUser entity = new AdminUser();
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        entity.setEnabled(dto.isEnabled());
        return entity;
    }

    @Override
    public void updateEntityFromDto(AdminUserRequestDto dto, AdminUser entity) {
        if (dto == null || entity == null) return;
        entity.setEmail(dto.getEmail());
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            entity.setPassword(dto.getPassword());
        }
        entity.setRole(dto.getRole());
        entity.setEnabled(dto.isEnabled());
    }
}

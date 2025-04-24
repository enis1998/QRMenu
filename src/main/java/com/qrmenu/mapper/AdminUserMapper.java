package com.qrmenu.mapper;

import com.qrmenu.dto.AdminUserDto;
import com.qrmenu.entity.AdminUser;

public interface AdminUserMapper {
    AdminUserDto toDto(AdminUser entity);
    AdminUser toEntity(AdminUserDto dto);
    void updateEntityFromDto(AdminUserDto dto, AdminUser entity);
}

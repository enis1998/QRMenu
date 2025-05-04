package com.qrmenu.mapper.User;

import com.qrmenu.dto.User.requests.AdminUserRequestDto;
import com.qrmenu.dto.User.responses.AdminUserResponseDto;
import com.qrmenu.entity.AdminUser;

public interface AdminUserMapper {
    AdminUserResponseDto toDto(AdminUser entity);
    AdminUser toEntity(AdminUserRequestDto dto);
    void updateEntityFromDto(AdminUserRequestDto dto, AdminUser entity);
}

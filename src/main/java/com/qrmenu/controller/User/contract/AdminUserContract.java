package com.qrmenu.controller.User.contract;

import com.qrmenu.dto.User.requests.AdminUserRequestDto;
import com.qrmenu.dto.User.responses.AdminUserResponseDto;

import java.util.List;
import java.util.UUID;

public interface AdminUserContract {

    List<AdminUserResponseDto> getAllUsers();

    AdminUserResponseDto getUserById(UUID id);

    AdminUserResponseDto createUser(AdminUserRequestDto dto);

    AdminUserResponseDto updateUser(UUID id, AdminUserRequestDto dto);

    void deleteUser(UUID id);

}

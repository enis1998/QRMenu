package com.qrmenu.controller.contract;

import com.qrmenu.dto.AdminUserDto;

import java.util.List;

public interface AdminUserContract {

    List<AdminUserDto> getAllUsers();

    AdminUserDto getUserById(Long id);

    AdminUserDto createUser(AdminUserDto dto);

    AdminUserDto updateUser(Long id, AdminUserDto dto);

    void deleteUser(Long id);

}

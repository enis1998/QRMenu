package com.qrmenu.dto.User.responses;

import com.qrmenu.enums.Role;

import javax.validation.constraints.*;
import java.util.UUID;

public class AdminUserResponseDto {

    private UUID id;

    @NotBlank(message = "{validation.email.notBlank}")
    @Email(message = "{validation.email.invalid}")
    @Size(min = 3, max = 50)
    private String email;

    @NotBlank(message = "{validation.password.notBlank}")
    @Size(min = 8, message = "{validation.password.size}")
    // en az bir büyük harf, bir rakam ve bir özel karakter
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).+$",
            message = "{validation.password.pattern}")
    private String password;

    @NotNull(message = "{validation.role.notNull}")
    private Role role;

    private boolean enabled;

    public AdminUserResponseDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

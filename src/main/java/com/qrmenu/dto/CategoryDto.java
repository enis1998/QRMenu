package com.qrmenu.dto;

import javax.validation.constraints.NotBlank;

public class CategoryDto {
    private Long id;

    @NotBlank(message = "Kategori adı boş olamaz")
    private String name;

    private boolean active = true;

    public CategoryDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

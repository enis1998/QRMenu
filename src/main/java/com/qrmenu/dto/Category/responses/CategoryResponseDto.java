package com.qrmenu.dto.Category.responses;

import com.qrmenu.enums.Status;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class CategoryResponseDto {
    private UUID id;

    @NotBlank(message = "Kategori adı boş olamaz")
    private String name;

    private Status status = Status.ACTIVE;

    public CategoryResponseDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

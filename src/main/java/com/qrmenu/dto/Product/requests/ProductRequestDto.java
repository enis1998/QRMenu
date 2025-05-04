package com.qrmenu.dto.Product.requests;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qrmenu.entity.Category;
import com.qrmenu.enums.Status;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JsonIgnoreProperties("category")
public class ProductRequestDto {
    private UUID id;

    @NotBlank(message = "Ürün adı boş olamaz")
    private String name;

    @NotBlank(message = "Açıklama boş olamaz")
    private String description;

    @NotNull
    @Min(value = 0, message = "Fiyat negatif olamaz")
    private Double price;

    private Status status = Status.ACTIVE;

    private Category category;

    private byte[] image;

    private String imageContentType;

    public ProductRequestDto() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }
}

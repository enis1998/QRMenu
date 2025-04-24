package com.qrmenu.entity;

import jakarta.persistence.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ürün adı boş olamaz")
    private String name;

    @Column(length = 1000)
    @NotBlank(message = "Açıklama boş olamaz")
    private String description;

    @NotBlank(message = "Fiyat girilmelidir")
    @Min(value = 0, message = "Fiyat negatif olamaz")
    private Double price;

    private boolean active = true;

    // Resim verisini veritabanında saklamak için BLOB kullanımı
    @Lob
    @Column(name = "image_data")
    private byte[] image;

    // Resmin MIME tipini saklamak için
    private String imageContentType;

    @Column(nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private Long parentId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {
    }

    public Product(String name, String description, Double price, boolean active, byte[] image, String imageContentType, Long parentId, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = active;
        this.image = image;
        this.imageContentType = imageContentType;
        this.parentId = parentId;
        this.category = category;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

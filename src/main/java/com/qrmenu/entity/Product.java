package com.qrmenu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qrmenu.enums.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @NotBlank(message = "Ürün adı boş olamaz")
    private String name;

    @Column(length = 1000)
    @NotBlank(message = "Açıklama boş olamaz")
    private String description;

    @NotBlank(message = "Fiyat girilmelidir")
    @Min(value = 0, message = "Fiyat negatif olamaz")
    private Double price;

    private Status status = Status.ACTIVE;

    // Resim verisini veritabanında saklamak için BLOB kullanımı
    @Lob
    @Column(
            name = "image_data",
            columnDefinition = "LONGBLOB"   // veya "MEDIUMBLOB"
    )
    private byte[] image;

    // Resmin MIME tipini saklamak için
    private String imageContentType;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("products")
    private Category category;

    public Product() {
    }

    public Product(String name, String description, Double price, Status status, byte[] image, String imageContentType, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.image = image;
        this.imageContentType = imageContentType;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

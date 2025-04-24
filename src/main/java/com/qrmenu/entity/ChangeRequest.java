package com.qrmenu.entity;

import com.qrmenu.enums.RequestStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ChangeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String newName;

    @Column(length = 1000)
    private String newDescription;

    private Double newPrice;

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;

    private String initiatedBy;

    private LocalDateTime requestedAt = LocalDateTime.now();

    public ChangeRequest() {
    }

    public ChangeRequest(Product product, String newName, String newDescription, Double newPrice, RequestStatus status, String initiatedBy, LocalDateTime requestedAt) {
        this.product = product;
        this.newName = newName;
        this.newDescription = newDescription;
        this.newPrice = newPrice;
        this.status = status;
        this.initiatedBy = initiatedBy;
        this.requestedAt = requestedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getInitiatedBy() {
        return initiatedBy;
    }

    public void setInitiatedBy(String initiatedBy) {
        this.initiatedBy = initiatedBy;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }
}

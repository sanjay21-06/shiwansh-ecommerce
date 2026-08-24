package com.shiwansh.ecommerce.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String imageUrl;

    private boolean active;


    public CategoryEntity() {
    }


    // ==============================
    // GETTERS
    // ==============================

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }


    public String getImageUrl() {
        return imageUrl;
    }


    public boolean isActive() {
        return active;
    }


    // ==============================
    // SETTERS
    // ==============================

    public void setId(Long id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public void setActive(boolean active) {
        this.active = active;
    }
}
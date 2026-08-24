package com.shiwansh.ecommerce.domain.model;

public class Category {

    private Long id;

    private String name;

    private String description;

    private String imageUrl;

    private boolean active;


    // ==============================
    // CONSTRUCTORS
    // ==============================

    public Category() {
    }


    public Category(
            Long id,
            String name,
            String description,
            String imageUrl,
            boolean active) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.active = active;
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
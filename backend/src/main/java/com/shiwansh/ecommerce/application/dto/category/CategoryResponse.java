package com.shiwansh.ecommerce.application.dto.category;

public class CategoryResponse {

    private Long id;

    private String name;

    private String description;

    private String imageUrl;

    private boolean active;


    // ==============================
    // CONSTRUCTOR
    // ==============================

    public CategoryResponse(
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
}
package com.shiwansh.ecommerce.application.dto.category;

public class CategoryCreateRequest {

    private String name;

    private String description;

    private String imageUrl;


    public CategoryCreateRequest() {
    }


    public CategoryCreateRequest(
            String name,
            String description,
            String imageUrl) {

        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }


    // =========================
    // GETTERS
    // =========================

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }


    public String getImageUrl() {
        return imageUrl;
    }


    // =========================
    // SETTERS
    // =========================

    public void setName(String name) {
        this.name = name;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
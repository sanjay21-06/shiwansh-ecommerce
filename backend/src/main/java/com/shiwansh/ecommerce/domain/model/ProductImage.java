package com.shiwansh.ecommerce.domain.model;

public class ProductImage {

    private Long id;
    private Product product;
    private String imageUrl;
    private boolean primary;

    public ProductImage() {
    }

    public ProductImage(Long id, Product product, String imageUrl, boolean primary) {
        this.id = id;
        this.product = product;
        this.imageUrl = imageUrl;
        this.primary = primary;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
}
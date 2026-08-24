package com.shiwansh.ecommerce.domain.model;

import java.math.BigDecimal;

public class ProductVariant {

    private Long id;
    private Product product;
    private String size;
    private String color;
    private BigDecimal additionalPrice;

    public ProductVariant() {
    }

    public ProductVariant(Long id, Product product, String size,
                          String color, BigDecimal additionalPrice) {
        this.id = id;
        this.product = product;
        this.size = size;
        this.color = color;
        this.additionalPrice = additionalPrice;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getAdditionalPrice() {
        return additionalPrice;
    }

    public void setAdditionalPrice(BigDecimal additionalPrice) {
        this.additionalPrice = additionalPrice;
    }
}
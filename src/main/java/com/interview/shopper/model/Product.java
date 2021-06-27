package com.interview.shopper.model;


public class Product {
    private Long productId;
    private String name;
    private Double price;

    public Product(Long productId, String name, Double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}

package com.interview.shopper.model;

public class ShoppingCartItem extends Product {
    private Integer qty;

    public ShoppingCartItem(Long id, String name, Double price, Integer qty) {
        super(id, name, price);
        this.qty = qty;
    }

    public Integer getQty() {
        return qty;
    }
}

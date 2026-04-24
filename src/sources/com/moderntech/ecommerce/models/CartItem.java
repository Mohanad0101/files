package com.moderntech.ecommerce.models;

public record CartItem(Product product, int quantity) {
    public double lineTotal() {
        return product.price() * quantity;
    }
}

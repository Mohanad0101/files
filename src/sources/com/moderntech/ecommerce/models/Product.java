package com.moderntech.ecommerce.models;

import com.moderntech.ecommerce.enums.ProductCategory;

public record Product(
        String id,
        String name,
        ProductCategory category,
        double price,
        int stock
) {
}

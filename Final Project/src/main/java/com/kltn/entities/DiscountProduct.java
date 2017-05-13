package com.kltn.entities;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Created by TinNguyen on 5/5/17.
 * Class using to add data toList in 1 document
 */

public class DiscountProduct {
    @DBRef
    private Product productId;
    private double discount;

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}

package com.kltn.entities;

import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Created by TinNguyen on 6/9/17.
 */
public class ImportModel {
    @DBRef
    private Product productId;
    private double quantity;
    private double originPrice;

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(double originPrice) {
        this.originPrice = originPrice;
    }
}

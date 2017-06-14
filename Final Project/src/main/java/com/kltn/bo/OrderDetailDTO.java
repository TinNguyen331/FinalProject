package com.kltn.bo;

import org.bson.types.ObjectId;

/**
 * Created by TinNguyen on 5/14/17.
 */
public class OrderDetailDTO {
    private String productId;
    private double quantity;
    private double price;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

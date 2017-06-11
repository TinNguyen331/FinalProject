package com.kltn.bo;

import org.bson.types.ObjectId;

/**
 * Created by TinNguyen on 5/14/17.
 */
public class OrderDetail {
    private ObjectId productId;
    private double quantity;
    private double price;

    public ObjectId getProductId() {
        return productId;
    }

    public void setProductId(ObjectId productId) {
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

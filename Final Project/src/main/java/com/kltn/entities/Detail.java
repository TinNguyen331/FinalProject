package com.kltn.entities;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Created by TinNguyen on 5/5/17.
 * Class using to add data toList in 1 document
 */
public class Detail {

    @DBRef
    private Product productId;
    private double quantity;
    private double price;

    public Detail(Product productId, double quantity,double price) {
        this.productId=productId;
        this.quantity=quantity;
        this.price=price;
    }

    public Product getProductId() {
        return productId;
    }

    public String getProductIdString(){
        return productId.getId();
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

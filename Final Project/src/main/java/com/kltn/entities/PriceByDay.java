package com.kltn.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by TinNguyen on 5/12/17.
 */
@Document(collection = "pricebyday")
public class PriceByDay {
    @Id
    private ObjectId id;
    @DBRef
    private Product productId;
    private Date date;
    private double price;

    public PriceByDay(Product productId) {
        this.productId = productId;
        this.price=productId.getProductPrice();
        this.date=new Date();
    }

    public String getId() {
        return id.toString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}

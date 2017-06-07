package com.kltn.bo;

/**
 * Created by TinNguyen on 6/7/17.
 */
public class PriceByDayDTO {

    private String productId;
    private double price;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

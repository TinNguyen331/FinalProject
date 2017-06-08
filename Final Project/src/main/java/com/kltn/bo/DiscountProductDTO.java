package com.kltn.bo;

/**
 * Created by TinNguyen on 6/8/17.
 */
public class DiscountProductDTO {

    private String productId;
    private double discount;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}

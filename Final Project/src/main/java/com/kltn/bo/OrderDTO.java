package com.kltn.bo;

import com.kltn.entities.Product;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by TinNguyen on 5/13/17.
 */
public class OrderDTO {

    private double totalCost;
    private String addressDelivery;
    private String receiver;
    private List<OrderDetailDTO> details;

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getAddressDelivery() {
        return addressDelivery;
    }

    public void setAddressDelivery(String addressDelivery) {
        this.addressDelivery = addressDelivery;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public List<OrderDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetailDTO> details) {
        this.details = details;
    }
}

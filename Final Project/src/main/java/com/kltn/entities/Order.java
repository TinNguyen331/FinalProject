package com.kltn.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;
import java.util.List;

/**
 * Created by TinNguyen on 5/5/17.
 */
@Document(collection = "order")
public class Order {
    @Id
    private ObjectId id;
    private Date dateOrder;
    private Date datePackage;
    private Date dateDelivery;
    private String status;
    private double totalCost;
    private String addressDelivery;
    private List<Detail> details;

    public Order(String _addressDelivery){
        this.addressDelivery=_addressDelivery;
        this.dateOrder=new Date();
    }

    public String getId() {
        return id.toString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Date getDatePackage() {
        return datePackage;
    }

    public void setDatePackage(Date datePackage) {
        this.datePackage = datePackage;
    }

    public Date getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(Date dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }


}

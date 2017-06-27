package com.kltn.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Calendar;
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
    private int day;
    private int month;
    private int year;
    private String status;
    private double totalCost;
    private String addressDelivery;
    private String receiver;
    private String phone;
    private List<Detail> details;
    private boolean isActive=true;

    public Order(String addressDelivery){
        Date toDay=new Date();
        Calendar cal=Calendar.getInstance();
        cal.setTime(toDay);
        this.addressDelivery=addressDelivery;
        this.dateOrder=toDay;
        this.day=cal.get(Calendar.DATE);
        this.month=cal.get(Calendar.MONTH);
        this.year=cal.get(Calendar.YEAR);
        this.status="RECEIVE";
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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

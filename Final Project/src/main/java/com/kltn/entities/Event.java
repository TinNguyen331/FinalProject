package com.kltn.entities;

import com.sun.org.apache.xpath.internal.operations.String;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Created by TinNguyen on 5/5/17.
 */
@Document(collection = "event")
public class Event {
    @Id
    private ObjectId id;
    private String eventName;
    private String eventPictureUrl;
    private Date fromDate;
    private Date toDate;
    private List<DiscountProduct> discountProducts;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventPictureUrl() {
        return eventPictureUrl;
    }

    public void setEventPictureUrl(String eventPictureUrl) {
        this.eventPictureUrl = eventPictureUrl;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public List<DiscountProduct> getDiscountProducts() {
        return discountProducts;
    }

    public void setDiscountProducts(List<DiscountProduct> discountProducts) {
        this.discountProducts = discountProducts;
    }
}

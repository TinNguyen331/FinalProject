package com.kltn.entities;

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
    private String eventMobilePicture;
    private Date fromDate;
    private Date toDate;
    private List<DiscountProduct> discountProducts;

    public String getId() {
        return id.toString();
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

    public String getEventMobilePicture() {
        return eventMobilePicture;
    }

    public void setEventMobilePicture(String eventMobilePicture) {
        this.eventMobilePicture = eventMobilePicture;
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

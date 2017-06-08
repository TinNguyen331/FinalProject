package com.kltn.bo;

import java.util.Date;
import java.util.List;

/**
 * Created by TinNguyen on 6/8/17.
 */
public class EventDTO {

    private String id;
    private String eventName;
    private String eventPictureUrl;
    private Date fromDate;
    private Date toDate;
    private List<DiscountProductDTO> discountProducts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<DiscountProductDTO> getDiscountProducts() {
        return discountProducts;
    }

    public void setDiscountProducts(List<DiscountProductDTO> discountProducts) {
        this.discountProducts = discountProducts;
    }
}

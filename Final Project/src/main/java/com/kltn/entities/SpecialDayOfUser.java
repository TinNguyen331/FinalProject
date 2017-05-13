package com.kltn.entities;

import java.util.Date;

/**
 * Created by TinNguyen on 5/12/17.
 */
public class SpecialDayOfUser {
    private Date date;
    private String description;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

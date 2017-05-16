package com.kltn.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by TinNguyen on 5/5/17.
 */
@Document(collection = "specialday")
public class SpecialDay {
    @Id
    private ObjectId id;
    private Date date;
    private String dateDescription;

    public String getId() {
        return id.toString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateDescription() {
        return dateDescription;
    }

    public void setDateDescription(String dateDescription) {
        this.dateDescription = dateDescription;
    }
}

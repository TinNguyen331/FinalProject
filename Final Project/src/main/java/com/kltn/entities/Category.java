package com.kltn.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by TinNguyen on 5/4/17.
 */
@Document(collection = "category")
public class Category {
    @Id
    private ObjectId id;
    private String categoryName;
    private String categoryPictureUrl;
    private boolean isActive;

    public Category(){}
    public Category(String categoryName){
        this.categoryName=categoryName;
    }

    public String getId() {
        return id.toString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryPictureUrl() {
        return categoryPictureUrl;
    }

    public void setCategoryPictureUrl(String categoryPictureUrl) {
        this.categoryPictureUrl = categoryPictureUrl;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

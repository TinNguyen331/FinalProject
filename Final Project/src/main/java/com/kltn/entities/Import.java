package com.kltn.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by TinNguyen on 6/9/17.
 */
@Document(collection = "import")
public class Import {
    @Id
    private ObjectId id;
    private Date date;
    private int month;
    private int year;
    private double totalCost;
    private List<ImportModel> importModels;

    public Import(){
        Date today=new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        this.date=today;
        this.month=cal.get(Calendar.MONTH);
        this.year=cal.get(Calendar.YEAR);
    }

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

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public List<ImportModel> getImportModels() {
        return importModels;
    }

    public void setImportModels(List<ImportModel> importModels) {
        this.importModels = importModels;
    }
}

package com.kltn.bo;

import com.kltn.entities.Detail;
import com.kltn.entities.Product;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by TinNguyen on 5/13/17.
 */
public class OrderUser {
    private String idUser;
    private List<OrderDetail> details;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(ObjectId String) {
        this.idUser = idUser;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }
}

package com.kltn.bo;

import com.kltn.entities.Detail;
import com.kltn.entities.Product;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by TinNguyen on 5/13/17.
 */
public class OrderUser {
    private ObjectId idUser;
    private List<OrderDetail> details;

    public ObjectId getIdUser() {
        return idUser;
    }

    public void setIdUser(ObjectId idUser) {
        this.idUser = idUser;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }
}

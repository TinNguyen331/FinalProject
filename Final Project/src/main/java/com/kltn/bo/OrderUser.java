package com.kltn.bo;

import com.kltn.entities.Product;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by TinNguyen on 5/13/17.
 */
public class OrderUser {
    private ObjectId idUser;
    private List<Product> productList;

    public ObjectId getIdUser() {
        return idUser;
    }

    public void setIdUser(ObjectId idUser) {
        this.idUser = idUser;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}

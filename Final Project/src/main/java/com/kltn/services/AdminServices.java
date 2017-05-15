package com.kltn.services;

import com.kltn.entities.*;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by TinNguyen on 5/14/17.
 */
public interface AdminServices {

    //region User

    long countUser();
    User insertOrUpdateUser(User entity);
    boolean deleteUser(ObjectId objectId);

    //endregion

    //region Category
    long countCategory();
    Category insertOrUpdateCategory(Category entity);
    boolean deleteCategory(ObjectId objectId);
    //endregion

    //region Product
    long countProduct();
    Product insertOrUpdateProduct(Product entity);
    boolean deleteProduct(ObjectId objectId);
    //endregion

    //region Event
    Event insertOrUpdateEvent(Event entity);
    boolean deleteEvent(ObjectId objectId);
    //endregion

    //region Notify
    Notify inserOrUpdateNotify(Notify entity);
    //endregion

    //region Order
    long countOrder();
    List<Order> getAllOrder();
    Order insertOrUpdateOrder(Order entity);
    //endregion

    //region SpecialDay
    SpecialDay insertOrUpdateSpecialDay(SpecialDay entity);
    //endregion

    //region PriceByDay
    List<PriceByDay> getAllPriceByDay();
    PriceByDay insertOrUpdatePriceByDay(PriceByDay priceByDay);
    //endregion
}

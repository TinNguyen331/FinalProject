package com.kltn.services;

import com.kltn.bo.OrderUser;
import com.kltn.entities.*;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by TinNguyen on 5/4/17.
 */
public interface CustomerServices {

    //region User

    List<User> getAllUser();
    User getUserById(ObjectId objectId);
    User updateOrderListOfUser(OrderUser entity);

    //endregion

    //region Category

    List<Category> getAllCategory();
    Category getCategoryById(ObjectId objectId);
    //endregion

    //region Product
    Product getProductById(ObjectId objectId);
    //endregion

    //region Event
    Event getEventById(ObjectId objectId);
    List<Event> getAllEvent();
    //endregion

    //region Notify
    Notify getNotifyByStatus(String status);
    Notify getNotifyById(ObjectId objectId);
    //endregion

    //region Order
    Order getOrderById(ObjectId objectId);
    //endregion

    //region SpecialDay
    List<SpecialDay> getAllSpecialDay();
    SpecialDay getSpecialDayById(ObjectId objectId);
    //endregion

    //region Blog
    List<Blog> getAllBlog();
    List<Blog> getRandomnBlog(int n);
    Blog getBlogById(ObjectId objectId);
    //endregion

    //region FlowerMeaning
    List<Meaning> getAllMeaning();
    List<Meaning> getRandomMeaning(int n);
    Meaning getMeaningById(ObjectId objectId);
    //endregion
}

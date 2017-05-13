package com.kltn.services;

import com.kltn.bo.OrderUser;
import com.kltn.entities.Category;
import com.kltn.entities.User;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by TinNguyen on 5/4/17.
 */
public interface CustomerServices {

    //region User
    long countUser();
    List<User> getAllUser();
    User getUserById(ObjectId objectId);
    User insertOrUpdateUser(User entity);
    User updateOrderListOfUser(OrderUser entity);
    User deleteUser(ObjectId objectId);
    //endregion

    //region Category
    List<Category> getAllCategory();
    //endregion

}

package com.kltn.services;

import com.kltn.entities.User;
import org.bson.types.ObjectId;

/**
 * Created by TinNguyen on 5/14/17.
 */
public interface AdminServices {
    User insertOrUpdateUser(User entity);
    boolean deleteUser(ObjectId objectId);
}

package com.kltn.services.servicesImplement;

import com.kltn.entities.User;
import com.kltn.repositories.UserRepository;
import com.kltn.services.AdminServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by TinNguyen on 5/14/17.
 */
@Service("AdminServices")
public class AdminServicesImpl implements AdminServices {

    //region Repository
    @Autowired
    private UserRepository userRepository;
    //endregion

    //region User
    @Override
    public User insertOrUpdateUser(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public boolean deleteUser(ObjectId objectId) {
        try{
            userRepository.delete(objectId);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }
    //endregion
}

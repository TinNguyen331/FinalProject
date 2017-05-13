package com.kltn.services.servicesImplement;

import com.kltn.bo.OrderUser;
import com.kltn.entities.Category;
import com.kltn.entities.User;
import com.kltn.repositories.CategoryRepository;
import com.kltn.repositories.UserRepository;
import com.kltn.services.CustomerServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mongodb.DBCollection;

import java.util.List;

/**
 * Created by TinNguyen on 5/4/17.
 */
@Service("CustomerServices")
public class CustomerServicesImpl implements CustomerServices {

    //region User
    @Autowired
    private UserRepository userRepository;

    @Override
    public long countUser() {
        return userRepository.count();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(ObjectId objectId) {
        return userRepository.findOne(objectId);
    }

    @Override
    public User insertOrUpdateUser(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public User updateOrderListOfUser(OrderUser entity) {
        return null;
    }

    @Override
    public User deleteUser(ObjectId objectId) {
        return null;
    }
    //endregion

    //region Category
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
    //endregion
}

package com.kltn.services.servicesImplement;

import com.kltn.bo.OrderDetail;
import com.kltn.bo.OrderUser;
import com.kltn.entities.*;
import com.kltn.repositories.CategoryRepository;
import com.kltn.repositories.OrderRepository;
import com.kltn.repositories.ProductRepository;
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

    //region Repository
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    //endregion

    //region User
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
    public User updateOrderListOfUser(OrderUser entity) {
        User usr=userRepository.findOne(entity.getIdUser());
        //Create new Order
        Order order=new Order(usr.getAddress());
        //Add detail to new Order
        for (OrderDetail detail:entity.getDetails()) {
            Product pro=productRepository.findOne(detail.getProductId());
            Detail detl=new Detail(pro,detail.getQuantity());
            order.getDetails().add(detl);
        }
        //Add new Order to MongoDB,After Save order will have new ObjectId
        orderRepository.save(order);
        usr.getOrderList().add(order);
        userRepository.save(usr);
        return usr;
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

package com.kltn.services.servicesImplement;

import com.kltn.bo.OrderDetail;
import com.kltn.bo.OrderUser;
import com.kltn.entities.*;
import com.kltn.repositories.*;
import com.kltn.services.CustomerServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.mongodb.DBCollection;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private NotifyRepository notifyRepository;
    @Autowired
    private SpecialDayRepository specialDayRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private MeaningRepository meaningRepository;
    //endregion

    //region User

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
        ObjectId objectId=new ObjectId(entity.getIdUser());
        User usr=userRepository.findOne(objectId);
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

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override

    public Category getCategoryById(ObjectId objectId){
        return categoryRepository.findOne(objectId);
    }

    //endregion

    //region Product
    @Override
    public Product getProductById(ObjectId objectId) {
        return productRepository.findOne(objectId);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByCategoryId(ObjectId cateId) {

        return productRepository.findAllProductByCateId(cateId);
    }
    //endregion

    //region Event
    @Override
    public Event getEventById(ObjectId objectId) {
        return eventRepository.findOne(objectId);
    }

    @Override
    public List<Event> getAllEvent() {
        return eventRepository.findAll();
    }

    //endregion

    //region Notify
    @Override
    public List<Notify> getNotifyByStatus(String status) {
        return notifyRepository.getNotifyByStatus(status);
    }

    @Override
    public Notify getNotifyById(ObjectId objectId) {
        return notifyRepository.findOne(objectId);
    }

    //endregion

    //region Order
    @Override
    public Order getOrderById(ObjectId objectId) {
        return orderRepository.findOne(objectId);
    }
    //endregion

    //region SpecialDay
    @Override
    public List<SpecialDay> getAllSpecialDay() {
        return specialDayRepository.findAll();
    }

    //endregion

    //region Blog
    @Override
    public SpecialDay getSpecialDayById(ObjectId objectId) {
        return specialDayRepository.findOne(objectId);
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogRepository.findAll();
    }

    //Get n random
    @Override
    public List<Blog> getRandomnBlog(int n) {
        Random rand=new Random();
        List<Blog> sourceList=blogRepository.findAll();
        List<Blog> finalList=rand.ints(n,0,sourceList.size()).mapToObj(i->sourceList.get(i)).collect(Collectors.toList());
        return finalList;
    }

    @Override
    public Blog getBlogById(ObjectId objectId) {
        return blogRepository.findOne(objectId);
    }

    //endregion

    //region Meaning
    @Override
    public List<Meaning> getAllMeaning() {
        return meaningRepository.findAll();
    }

    @Override
    public List<Meaning> getRandomMeaning(int n) {
        Random rand=new Random();
        List<Meaning> sourceList=meaningRepository.findAll();
        List<Meaning> finalList=rand.ints(n,0,sourceList.size()).mapToObj(i->sourceList.get(i)).collect(Collectors.toList());
        return finalList;
    }

    @Override
    public Meaning getMeaningById(ObjectId objectId) {
        return meaningRepository.findOne(objectId);
    }
    //endregion
}

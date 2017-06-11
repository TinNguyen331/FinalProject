package com.kltn.services.servicesImplement;

import com.kltn.Util.UserUtil;
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

import java.util.Calendar;
import java.util.Date;
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
    @Autowired
    private UserUtil userUtil;
    //endregion

    //region User

    @Override
    public List<User> getAllUser() {
        return userUtil.findAllActiveUserExpectAdmin();
    }

    @Override
    public User getUserById(ObjectId objectId) {
        User user=userRepository.findOne(objectId);
        return user.isActive()?user:null;
    }

    @Override
    public User updateOrderListOfUser(OrderUser entity) {
//        ObjectId objectId=new ObjectId(entity.getIdUser());
//        User usr=userRepository.findOne(objectId);
//        String address=usr.getAddress().get(usr.getActiveIndexAddress());
//        //Create new Order
//        Order order=new Order(address);
//        //Add detail to new Order
//        for (OrderDetail detail:entity.getDetails()) {
//            Product pro=productRepository.findOne(detail.getProductId());
//            Detail detl=new Detail(pro,detail.getQuantity());
//            order.getDetails().add(detl);
//        }
//        //Add new Order to MongoDB,After Save order will have new ObjectId
//        orderRepository.save(order);
//        usr.getOrderList().add(order);
//        userRepository.save(usr);
        return null;
    }


    //endregion

    //region Category

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findByisActive(true);
    }

    @Override

    public Category getCategoryById(ObjectId objectId){
        Category cate=categoryRepository.findOne(objectId);
        return cate.isActive() ? cate:null;
    }

    //endregion

    //region Product
    @Override
    public Product getProductById(ObjectId objectId) {
        Product product=productRepository.findOne(objectId);
        return product.isActive()?product:null;
    }
    @Override
    public List<Product> getRandom(int n){
        Random rand=new Random();
        List<Product> sourceList=productRepository.findAll();
        List<Product> finalList=rand.ints(n,0,sourceList.size()).mapToObj(i->sourceList.get(i)).collect(Collectors.toList());
        return finalList;
    }
    @Override
    public List<Product> getAllProduct() {
        return productRepository.findByisActive(true);
    }

    @Override
    public List<Product> getProductByCategoryId(ObjectId cateId) {
        Category category=categoryRepository.findOne(cateId);
        return productRepository.findBycategoryId(category);
    }
    //endregion

    //region Event
    @Override
    public Event getEventById(ObjectId objectId) {

        return eventRepository.findOne(objectId);
    }

    @Override
    public List<Event> getAllEventNear(){
        Date date=new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,2);
        return eventRepository.findByfromDateGreaterThan(calendar.getTime(),new Sort(Sort.Direction.ASC,"fromDate"));
    }

    @Override
    public List<Event> getAllEvent() {
        return eventRepository.findAll();
    }

    //endregion

    //region Notify
    @Override
    public List<Notify> getNewestNotify() {
        return notifyRepository.findAll(new Sort(Sort.Direction.DESC,"date"));
    }

    @Override
    public List<Notify> getAllUnReadNotify(){
        return notifyRepository.findBystatus(false,new Sort(Sort.Direction.DESC,"date"));
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
        return specialDayRepository.findByisActive(true);
    }

    @Override
    public SpecialDay getSpecialDayById(ObjectId objectId) {
        SpecialDay specialDay=specialDayRepository.findOne(objectId);
        return specialDay.isActive()?specialDay:null;
    }
    //endregion

    //region Blog


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
       return  meaningRepository.findOne(objectId);

    }
    //endregion
}

package com.kltn.services.servicesImplement;

import com.kltn.entities.*;
import com.kltn.repositories.*;
import com.kltn.services.AdminServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TinNguyen on 5/14/17.
 */
@Service("AdminServices")
public class AdminServicesImpl implements AdminServices {

    //region Repository
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private NotifyRepository notifyRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private SpecialDayRepository specialDayRepository;
    @Autowired
    private PriceByDayRepository priceByDayRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private MeaningRepository meaningRepository;
    //endregion

    //region User
    @Override
    public long countUser() {
        return userRepository.count();
    }

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

    //region Category
    @Override
    public long countCategory() {
        return categoryRepository.count();
    }

    @Override
    public Category insertOrUpdateCategory(Category entity) {
        return categoryRepository.save(entity);
    }

    @Override
    public boolean deleteCategory(ObjectId objectId) {
        try{
            categoryRepository.delete(objectId);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    //endregion

    //region Product
    @Override
    public long countProduct() {
        return productRepository.count();
    }

    @Override
    public Product insertOrUpdateProduct(Product entity) {
        if(entity.getId()!="") {
            return productRepository.save(entity);
        }
        else { //insert New
            productRepository.save(entity);
            PriceByDay priceByDay=new PriceByDay(entity);
            priceByDayRepository.save(priceByDay);
            return entity;
        }
    }

    @Override
    public boolean deleteProduct(ObjectId objectId) {
        try{
            productRepository.delete(objectId);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }


    //endregion

    //region Event
    @Override
    public Event insertOrUpdateEvent(Event entity) {
        return eventRepository.save(entity);
    }

    @Override
    public boolean deleteEvent(ObjectId objectId) {
        try{
            eventRepository.delete(objectId);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }


    //endregion

    //region Notify
    @Override
    public Notify inserOrUpdateNotify(Notify entity) {
        return notifyRepository.save(entity);
    }

    //endregion

    //region Order
    @Override
    public long countOrder() {
        return orderRepository.count();
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllOrderCompletedOrUnCompleted(boolean completed) {
        if(completed){
            return orderRepository.findAllCompletedOrder();
        }
        else {
            return orderRepository.findAllUncompletedOrder();
        }
    }

    @Override
    public Order insertOrUpdateOrder(Order entity) {
        return orderRepository.save(entity);
    }

    //endregion

    //region SpecialDay
    @Override
    public SpecialDay insertOrUpdateSpecialDay(SpecialDay entity) {
        return specialDayRepository.save(entity);
    }

    @Override
    public boolean deleteSpecialDay(ObjectId objectId) {
        try{
            specialDayRepository.delete(objectId);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    //endregion

    //region PriceByDay
    @Override
    public List<PriceByDay> getAllPriceByDay() {
        return priceByDayRepository.findAll();
    }

    //If insert Product much be exits
    @Override
    public PriceByDay insertOrUpdatePriceByDay(PriceByDay priceByDay) {
        ObjectId objectId=new ObjectId(priceByDay.getProductId().getId());
        if(productRepository.exists(objectId))
        {
            priceByDayRepository.save(priceByDay);
            //update price product
            Product pro=productRepository.findOne(objectId);
            pro.setProductPrice(priceByDay.getPrice());
            productRepository.save(pro);
            return priceByDay;
        }
        return null;
    }


    //endregion

    //region Blog
    @Override
    public Blog insertOrUpdateBlog(Blog entity) {
        return blogRepository.save(entity);
    }

    @Override
    public Meaning insertOrUpdateMeaning(Meaning entity) {
        return meaningRepository.save(entity);
    }
    //endregion
}

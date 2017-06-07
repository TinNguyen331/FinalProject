package com.kltn.services.servicesImplement;

import com.kltn.Util.PriceByDayUtil;
import com.kltn.Util.TempPriceByDay;
import com.kltn.entities.*;
import com.kltn.repositories.*;
import com.kltn.services.AdminServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private PriceByDayUtil priceByDayUtil;
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
            User user=userRepository.findOne(objectId);
            user.setActive(false);
            user.setEnabled(false);
            userRepository.save(user);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }
    @Override
    public boolean activeUser(ObjectId objectId){
        try {
            User user=userRepository.findOne(objectId);
            if(user.isActive())
                return false;
            user.setActive(true);
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    //endregion

    //region Category
    @Override
    public long countCategory() {
        return categoryRepository.findByisActive(true).size();
    }

    @Override
    public Category insertOrUpdateCategory(Category entity) {
        return categoryRepository.save(entity);
    }

    @Override
    public boolean deleteCategory(ObjectId objectId) {
        try{
            Category category=categoryRepository.findOne(objectId);
            category.setActive(false);
            categoryRepository.save(category);
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
        if(!entity.getId().isEmpty()) {
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
            Product product=productRepository.findOne(objectId);
            product.setActive(false);
            productRepository.save(product);
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
            SpecialDay specialDay=specialDayRepository.findOne(objectId);
            specialDay.setActive(false);
            specialDayRepository.save(specialDay);
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
        List<PriceByDay> lst=new ArrayList<>();
        List<TempPriceByDay> lstTemp=priceByDayUtil.getAllLatestPriceByDayDistinct();
        for (TempPriceByDay temp:lstTemp
             ) {
            PriceByDay priceByDay=priceByDayRepository.findOne(new ObjectId(temp.getPricebydayId()));
            lst.add(priceByDay);
        }
        return lst;
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

package com.kltn.services.servicesImplement;

import com.kltn.Util.PriceByDayUtil;
import com.kltn.Util.TempPriceByDay;
import com.kltn.bo.ChartDTO;
import com.kltn.bo.OrderStatisticalDTO;
import com.kltn.entities.*;
import com.kltn.repositories.*;
import com.kltn.services.AdminServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    private ImportRepository importRepository;
    @Autowired
    private PriceByDayUtil priceByDayUtil;
    //endregion

    //region User
    @Override
    public long countUser() {
        return userRepository.count();
    }


    @Override
    public User getUserByName(String name){
        return userRepository.findByUserName(name);
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
    public List<Order> getAllNewOrder() {
        return orderRepository.findByisActiveAndStatus(true,"RECEIVE",new Sort(Sort.Direction.DESC,"dateOrder"));
    }

    @Override
    public List<Order> getAllSendingOrder(){
        return orderRepository.findByisActiveAndStatus(true,"SENDING",new Sort(Sort.Direction.DESC,"dateOrder"));
    }

    @Override
    public List<Order> getAllCompletedOrder(){
        return orderRepository.findByisActiveAndStatus(true,"DELIVERY",new Sort(Sort.Direction.DESC,"dateOrder"));
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
    public Order insertOrder(Order entity) {
        try {
            for (Detail detail : entity.getDetails()
                    ) {
                Product product = detail.getProductId();
                product.setQuantityInStock(product.getQuantityInStock() - detail.getQuantity());
                productRepository.save(product);
            }

            return orderRepository.save(entity);
        }
        catch (Exception ex){
            return null;
        }
    }

    @Override
    public Order updateOrder(Order entity){
        return orderRepository.save(entity);
    }

    @Override
    public boolean deleteOrder(ObjectId id){

        try {
            Order order = orderRepository.findOne(id);

            if(!order.isActive())
                return false;
            order.setActive(false);

            //Return Product
            for (Detail detail : order.getDetails()
                    ) {
                Product product = detail.getProductId();
                product.setQuantityInStock(product.getQuantityInStock() + detail.getQuantity());
                productRepository.save(product);
            }
            orderRepository.save(order);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    @Override
    public OrderStatisticalDTO getRevenue(){
        OrderStatisticalDTO orderStatisticalDTO=new OrderStatisticalDTO();
        Date toDay=new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(toDay);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        //Set start
        Date start=calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        Date end=calendar.getTime();

        int month=calendar.get(Calendar.MONTH);
        int year=calendar.get(Calendar.YEAR);

        List<Order> orderList=orderRepository.findByisActiveAndDateOrderBetween(true,start,end);
        List<Order> orderListCompleted=orderRepository.findByisActiveAndDateDeliveryBetweenAndStatus(true,start,end,"DELIVERY");
        List<Order> orderListMonth=orderRepository.findByisActiveAndMonthAndYearAndStatus(true,month,year,"DELIVERY");

        double totalCostToday=orderListCompleted.stream().filter(o->o.getTotalCost()>0).mapToDouble(Order::getTotalCost).sum();
        double totalCostThisMonth=orderListMonth.stream().filter(o->o.getTotalCost()>0).mapToDouble(Order::getTotalCost).sum();

        orderStatisticalDTO.setNumberOrderToday(orderList.size());
        orderStatisticalDTO.setRevenueOrderToday(totalCostToday);
        orderStatisticalDTO.setRevenueOrderThisMonth(totalCostThisMonth);

        return orderStatisticalDTO;
    }

    @Override
    public ChartDTO caculateProfit(){
        ChartDTO chartDTO=new ChartDTO();
        Date toDay=new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(toDay);

        int year=calendar.get(Calendar.YEAR);

        List<Import> imports=importRepository.findByYear(year,new Sort(Sort.Direction.ASC,"month"));
        List<Order> orders=orderRepository.findByisActiveAndYearAndStatus(true,year,"DELIVERY",new Sort(Sort.Direction.ASC,"month"));

        Map<Integer,Double> importList=imports.stream().collect(
                Collectors.groupingBy(Import::getMonth,Collectors.summingDouble(Import::getTotalCost)));

        Map<Integer,Double> orderList=orders.stream().collect(
                Collectors.groupingBy(Order::getMonth,Collectors.summingDouble(Order::getTotalCost)));

        chartDTO.setListCost(new ArrayList<Double>(importList.values()));
        chartDTO.setListRevenue(new ArrayList<Double>(orderList.values()));

        return chartDTO;
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
    @Override
    public List<PriceByDay> getAllPriceByDayByProduct(ObjectId productId){
        Product product=productRepository.findOne(productId);
        return priceByDayRepository.findByproductId(product);
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

    @Override
    public Import insertOrUpdateImport(List<ImportModel> entity) {
        Import model=new Import();
        model.setImportModels(entity);
        double total=0;
        for (ImportModel item:entity
             ) {
            total+=item.getOriginPrice()*item.getQuantity();
            Product updateProduct=item.getProductId();
            double newQuantity=updateProduct.getQuantityInStock() +item.getQuantity();
            updateProduct.setQuantityInStock(newQuantity);
            productRepository.save(updateProduct);
        }
        model.setTotalCost(total);
        return importRepository.save(model);
    }
    //endregion
}

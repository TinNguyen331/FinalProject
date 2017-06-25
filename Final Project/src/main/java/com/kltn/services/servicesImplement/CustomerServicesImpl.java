package com.kltn.services.servicesImplement;

import com.kltn.Util.UserUtil;
import com.kltn.bo.OrderDTO;
import com.kltn.entities.*;
import com.kltn.repositories.*;
import com.kltn.services.CustomerServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
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
    public User updateOrderListOfUser(OrderDTO entity) {
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

    @Override
    public List<Product> getAllNewProduct(){

        List<Product> productsNew=productRepository.findByisNewTrue();
        List<Product> newAndActive=productsNew.stream().filter(product -> product.isNew()==true).collect(Collectors.toList());
        return newAndActive;
    }

    @Override
    public List<Product> getBestSellerProduct(){
        Date today=new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int month=cal.get(Calendar.MONTH);
        int year=cal.get(Calendar.YEAR);

        //Prepair list order
        List<Order> lstOrder=orderRepository.findByisActiveAndMonthAndYear(true,month,year);
        if(lstOrder.size()!=0) {
            //Group listOrder by Product and Couting quantity as value
            List<Detail> details = new ArrayList<>();

            for (Order order : lstOrder) {
                details.addAll(order.getDetails());
            }
            Map<String, Double> sum = details.stream().collect(Collectors.groupingBy(Detail::getProductIdString, Collectors.summingDouble(Detail::getQuantity)));

            //Sort Array in finalMap by Map Sum
            Map<String, Double> finalMap = new LinkedHashMap<>();
            sum.entrySet().stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue()
                            .reversed()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));


            //Add Product to list Product
            List<Product> lstProduct = new ArrayList<Product>();
            int i = 0;
            for (Map.Entry<String, Double> entry : finalMap.entrySet()) {
                if (i > 4)
                    break;
                String key = entry.getKey();
                lstProduct.add(productRepository.findOne(new ObjectId(key)));
                i++;
            }

            if (lstProduct.size() >= 5 )
                return lstProduct.subList(0, 5); //Get 5 product in list

            return lstProduct;
        }
        return null;
    }

    @Override
    public List<Product> getAllProductMayBeUserLike(User user){
        if(user==null)
            return null;
        if(user.getOrderList().size()!=0) {

            //Prepair list Cate User bought
            List<Category> categories = new ArrayList<>();

            for (Order order : user.getOrderList()
                    ) {
                for (Detail detail : order.getDetails()
                        ) {
                    categories.add(detail.getProductId().getCategoryId());
                }
            }
            //List which Cate user buy most
            Map<String, Long> counting = categories.stream().collect(
                    Collectors.groupingBy(Category::getId, Collectors.counting())
            );

            //Sort
            Map<String, Long> finalMap = new LinkedHashMap<>();
            counting.entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue()
                            .reversed()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

            //Get the 1st Cate
            Map.Entry<String, Long> entry = finalMap.entrySet().iterator().next();
            Category category = categoryRepository.findOne(new ObjectId(entry.getKey()));
            List<Product> products = productRepository.findBycategoryId(category);
            return products;
        }
        return getBestSellerProduct();
    }
    @Override
    public Double getDiscountProductInEvent(String productId){
        Double discount=0.0;
        Event event=getAllEventAlready();
        if(event!=null)
        {
            for (DiscountProduct discountProduct:event.getDiscountProducts()
                 ) {
                if(productId.equals(discountProduct.getProductId().getId()))
                    discount=discountProduct.getDiscount();
            }
        }
        return discount;
    }
    //endregion

    //region Event
    @Override
    public Event getEventById(ObjectId objectId) {

        return eventRepository.findOne(objectId);
    }

    @Override
    public List<Event> getAllEventNear(){

        return eventRepository.findByfromDateGreaterThan(new Date(),new Sort(Sort.Direction.ASC,"fromDate"));
    }

    @Override
    public List<Event> getAllEvent() {
        return eventRepository.findAll();
    }

    @Override
    public Event getAllEventAlready(){
        Date today=new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date fromDate=calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date toDay=calendar.getTime();
        Event event=eventRepository.findByFromDateLessThanEqualAndToDateGreaterThanEqual(fromDate,toDay);
        return event;
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

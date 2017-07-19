package com.kltn.Util;

import com.kltn.entities.Authority;
import com.kltn.entities.Event;
import com.kltn.entities.Order;
import com.kltn.entities.User;
import com.kltn.repositories.EventRepository;
import com.kltn.repositories.UserRepository;
import com.kltn.services.CustomerServices;
import de.bytefish.fcmjava.http.client.IFcmClient;
import de.bytefish.fcmjava.model.enums.ErrorCodeEnum;
import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.model.topics.Topic;
import de.bytefish.fcmjava.requests.notification.NotificationPayload;
import de.bytefish.fcmjava.requests.topic.TopicUnicastMessage;
import de.bytefish.fcmjava.responses.TopicMessageResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.*;

/**
 * Created by TinNguyen on 6/26/17.
 */

@Service
public class SendNotifyService {

    @Autowired
    private CustomerServices customerServices;

    @Autowired
    private EventRepository eventRepository;


    @Autowired
    private UserRepository userRepository;

    private final IFcmClient fcmClient;

    private int id = 0;

    public SendNotifyService(IFcmClient fcmClient) {
        this.fcmClient = fcmClient;
    }

    @Scheduled(cron = "0 6 * * * *")
    public void sendNotify(){
        //User user=userRepository.findOne(new ObjectId("59361b542baebf03df06d75e"));
        List<Authority> authorities=new ArrayList<>();
        authorities.add(new Authority(AuthorityName.ROLE_USER));
        for (User user:userRepository.findAll()
             ) {
            if(checkAuthorityUser(user.getAuthorities())){
                Order order=getOrderToDay(user);
                if(order!=null)
                    sendPushMessage(order);
                sendPushMessageEvent(getEventNear());
            }

        }
    }
    private boolean checkAuthorityUser(List<Authority> authorities){
        for (Authority authority:authorities){
            if(authority.getName()==AuthorityName.ROLE_USER)
                return true;
        }
        return false;
    }
    public Order getOrderToDay(User user){
        if(user.getOrderList().size()>0){
            Date date=new Date();
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date);
            List<Order> list=new ArrayList<>();
            int day=calendar.get(Calendar.DATE);
            int month=calendar.get(Calendar.MONTH);
            for (Order order:user.getOrderList()
                    ) {
                if(order.getDay()==day && order.getMonth()==month&&order.getStatus().equals("DELIVERY")){
                    list.add(order);
                }
            }
            Collections.sort(list, new Comparator<Order>() {
                @Override
                public int compare(Order o1, Order o2) {
                    return o2.getDateOrder().compareTo(o1.getDateOrder());
                }
            });
            return list.get(0);// Check Get max order day
        }
        return null;
    }

    public Event getEventNear(){
        Date toDay=new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(toDay);

        calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)+3);// Set next 3 days
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);

        List<Event> lstEvent=eventRepository.findByfromDateGreaterThan(calendar.getTime(),new Sort(Sort.Direction.DESC));
        if(lstEvent.size()==0)
            return null;
        return lstEvent.get(0);//Return 1st index in list
    }

    public void sendPushMessage(Order order) {
        FcmMessageOptions options = FcmMessageOptions.builder()
                .setTimeToLive(Duration.ofMinutes(2)).build();

        NotificationPayload payload = NotificationPayload.builder()
                .setBody("You have memory order today").setTitle("Suggest")
                .setTag("Message").build();

        Map<String, Object> data = new HashMap<>();
        data.put("id", ++this.id);
        data.put("type",1);
        data.put("text", order.getId());

        // Send a message
        System.out.println("Sending message...");

        Topic topic = new Topic("message");
        TopicUnicastMessage message = new TopicUnicastMessage(options, topic, data, payload);

        TopicMessageResponse response = this.fcmClient.send(message);
        System.out.println(response);
        ErrorCodeEnum errorCode = response.getErrorCode();
        if (errorCode != null) {
            System.out.println("Topic message sending failed: " + errorCode);
        }
    }
    public  void sendPushMessageEvent(Event event){
        if(event==null){
            System.out.println("Event null no message have been send");
            return;
        }

        FcmMessageOptions options = FcmMessageOptions.builder()
                .setTimeToLive(Duration.ofMinutes(2)).build();

        NotificationPayload payload = NotificationPayload.builder()
                .setBody("Have a new event, ready for this").setTitle("Suggest")
                .setTag("Message").build();

        Map<String, Object> data = new HashMap<>();
        data.put("id", ++this.id);
        data.put("type",2);
        data.put("text", event.getId());

        // Send a message
        System.out.println("Sending message...");

        Topic topic = new Topic("message");
        TopicUnicastMessage message = new TopicUnicastMessage(options, topic, data, payload);

        TopicMessageResponse response = this.fcmClient.send(message);
        System.out.println(response);
        ErrorCodeEnum errorCode = response.getErrorCode();
        if (errorCode != null) {
            System.out.println("Topic message sending failed: " + errorCode);
        }
    }
}

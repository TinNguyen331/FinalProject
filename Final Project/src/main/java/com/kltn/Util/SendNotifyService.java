package com.kltn.Util;

import de.bytefish.fcmjava.http.client.IFcmClient;
import de.bytefish.fcmjava.model.enums.ErrorCodeEnum;
import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.model.topics.Topic;
import de.bytefish.fcmjava.requests.notification.NotificationPayload;
import de.bytefish.fcmjava.requests.topic.TopicUnicastMessage;
import de.bytefish.fcmjava.responses.TopicMessageResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TinNguyen on 6/26/17.
 */
@Service
public class SendNotifyService {
    private final RestTemplate restTemplate;

    private final IFcmClient fcmClient;

    private int id = 0;

    public SendNotifyService(IFcmClient fcmClient) {
        this.restTemplate = new RestTemplate();
        this.fcmClient = fcmClient;
    }


    public void sendPushMessage(String joke) {
        FcmMessageOptions options = FcmMessageOptions.builder()
                .setTimeToLive(Duration.ofMinutes(2)).build();

        NotificationPayload payload = NotificationPayload.builder()
                .setBody("A new message has arrived").setTitle("Test FCM")
                .setTag("Message").build();

        Map<String, Object> data = new HashMap<>();
        data.put("id", ++this.id);
        data.put("text", joke);

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

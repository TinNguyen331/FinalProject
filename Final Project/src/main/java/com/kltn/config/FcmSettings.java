package com.kltn.config;

import de.bytefish.fcmjava.http.options.IFcmClientSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by TinNguyen on 6/26/17.
 */

@ConfigurationProperties(prefix = "fcm")
@Component
public class FcmSettings implements IFcmClientSettings {
    @Value("${fcm.api-key}")
    private String apiKey;
    @Value("${fcm.url}")
    private String url;

    @Override
    public String getFcmUrl() {
        return url;
    }

    @Override
    public String getApiKey() {
        return this.apiKey;
    }
}

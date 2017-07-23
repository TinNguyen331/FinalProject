package com.kltn;


import com.kltn.config.FcmSettings;
import com.kltn.entities.DiscountProduct;
import com.kltn.entities.Event;
import com.kltn.entities.Order;
import com.kltn.entities.User;
import com.kltn.repositories.ProductRepository;
import com.kltn.repositories.UserRepository;
import com.kltn.services.CustomerServices;
import de.bytefish.fcmjava.client.FcmClient;
import de.bytefish.fcmjava.http.client.IFcmClient;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;


@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
@SpringBootApplication
public class FinalProjectApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FinalProjectApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public IFcmClient fcmClient(FcmSettings settings) {
		return new FcmClient(settings);
	}


	@Autowired
	private CustomerServices customerServices;
	@Autowired
	private ProductRepository productRepository;
	@Override
	public void run(String... strings) throws Exception {

		List<Event> events=customerServices.getAllEventNear();
		for (Event event :events
				) {
			for (DiscountProduct discountProduct: event.getDiscountProducts()
				 ) {
				if(discountProduct.getProductId()==null){
					System.out.println("=============");
					System.out.println(event.getId());
					System.out.println(discountProduct.getDiscount());

				}
			}
		}
 	}
}

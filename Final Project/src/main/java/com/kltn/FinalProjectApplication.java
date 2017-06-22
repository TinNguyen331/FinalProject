package com.kltn;


import com.kltn.Util.PriceByDayUtil;
import com.kltn.Util.UserUtil;
import com.kltn.bo.ChartDTO;
import com.kltn.bo.OrderStatisticalDTO;
import com.kltn.entities.*;
import com.kltn.repositories.*;
import com.kltn.services.AdminServices;
import com.kltn.services.CustomerServices;
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
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Configuration
@ComponentScan
@EnableAutoConfiguration
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

	@Autowired
	AdminServices adminServices;
	@Autowired
	CustomerServices customerServices;

	@Override
	public void run(String... strings) throws Exception {

		//OrderStatisticalDTO orderStatisticalDTO=adminServices.getRevenue();
		//ChartDTO chartDTO=adminServices.caculateProfit();
		//List<Product> ls=customerServices.getBestSellerProduct();
//		User user=userRepository.findByUserName("user");
//		List<Product> ls=customerServices.getAllProductMayBeUserLike(user);


//		Order order=new Order("43/5 385");
//		order.setReceiver("Nguyen Thi Khanh Chung");
//		order.setTotalCost(200);
//		List<Detail> details=new ArrayList<>();
//
//		Product product=productRepository.findOne(new ObjectId("59396d2e333d2170654bf319"));
//		Detail detail=new Detail(product,100,1);
//
//		details.add(detail);
//
//		Product product1=productRepository.findOne(new ObjectId("59369e8e2baebf0b2aac692c"));
//		Detail detail2=new Detail(product1,20,2);
//		details.add(detail2);
//
//		Product product2=productRepository.findOne(new ObjectId("5936636c2baebf0b2aac6928"));
//		Detail detail3=new Detail(product2,30,2);
//		details.add(detail3);
//
//		order.setDetails(details);
//		orderRepository.save(order);
//
//		User user=userRepository.findByUserName("user");
//		user.getOrderList().add(order);
//		userRepository.save(user);


	}
}

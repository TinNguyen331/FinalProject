package com.kltn;


import com.kltn.Util.PriceByDayUtil;
import com.kltn.Util.UserUtil;
import com.kltn.entities.*;
import com.kltn.repositories.*;
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
public class FinalProjectApplication extends SpringBootServletInitializer {

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

//	@Autowired
//	CustomerServices customerServices;
//	@Autowired
//	NotifyRepository notifyRepository;
//	@Autowired
//	SpecialDayRepository specialDayRepository;
//
//	@Override
//	public void run(String... strings) throws Exception {
//
//
//		//SpecialDay specialDay=new SpecialDay();
//		//SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
//		//Calendar calendar=new GregorianCalendar(2017,5,1,11,24,32);
//
//		//specialDay.setDate(calendar.getTime());
//		//specialDay.setDateDescription("Children's Day ");
//		//specialDayRepository.save(specialDay);
//
//		//SpecialDay specialDay1=new SpecialDay();
//		//Calendar calenda2r=new GregorianCalendar(2017,5,6,11,24,32);
//		//specialDay1.setDate(calenda2r.getTime());
//		//specialDay1.setDateDescription("Bom's Birthday");
//		//specialDayRepository.save(specialDay1);
//
////		Notify notify=new Notify();
////		notify.setEmail("email1");
////		notify.setMessage("message1");
////		notify.setName("name1");
////		notify.setPhone("phone1");
//
////		Notify notify1=new Notify();
////		notify1.setEmail("email2");
////		notify1.setMessage("message2");
////		notify1.setName("name2");
////		notify1.setPhone("phone2");
////
////		notifyRepository.save(notify);
////		//notifyRepository.save(notify1);
////
////		List<Notify> lst=customerServices.getNewestNotify();
//
//	}
}

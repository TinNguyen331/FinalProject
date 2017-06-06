package com.kltn;


import com.kltn.entities.Category;
import com.kltn.repositories.AuthorityRepository;
import com.kltn.repositories.CategoryRepository;
import com.kltn.repositories.UserRepository;
import com.kltn.services.CustomerServices;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootApplication
public class FinalProjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Autowired
	AuthorityRepository authorityRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CustomerServices customerServices;
	@Autowired
	CategoryRepository categoryRepository;
	@Override
	public void run(String... strings) throws Exception {

		//Category cate=customerServices.getCategoryById(new ObjectId("593618352baebf028309a7d4"));
	//Category cate=categoryRepository.findOneByisActive(new ObjectId("5930fa8410e8c70633f38ab7"),true);
/*
		List<Category> categories=categoryRepository.findByisActive(true);
		for (Category cate:categories
				) {
			System.out.println(cate.getCategoryName());
		}

		authorityRepository.deleteAll();
		userRepository.deleteAll();

		Authority admin=new Authority(AuthorityName.ROLE_ADMIN);
		Authority user=new Authority(AuthorityName.ROLE_USER);
		authorityRepository.save(admin);
		authorityRepository.save(user);

		//Save new User
		//Add admin
		User ad=new User();
		ad.setUserName("admin");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode("123");

		ad.setPassWord(hashedPassword);
		List<Authority> lst=new ArrayList<>();
		lst.add(admin);
		ad.setAuthorities(lst);
		ad.setFullName("admin");
		ad.setEnabled(true);
		ad.setLastPasswordResetDate(new Date());

		//
		User ad2=new User();
		ad2.setUserName("admin2");
		String hashedPassword2 = passwordEncoder.encode("123");

		ad2.setPassWord(hashedPassword2);
		ad2.setAuthorities(lst);
		ad2.setFullName("admin2");
		ad2.setEnabled(true);
		ad2.setLastPasswordResetDate(new Date());

		//Add user
		User ur=new User();
		ur.setUserName("user");
		String hashedPassword3 = passwordEncoder.encode("123");

		ur.setPassWord(hashedPassword3);
		List<Authority> lst2=new ArrayList<>();
		lst2.add(user);
		ur.setAuthorities(lst2);
		ur.setFullName("user");
		ur.setEnabled(true);
		ur.setLastPasswordResetDate(new Date());

		userRepository.save(ad);
		userRepository.save(ad2);
		userRepository.save(ur);
		*/

	}
}

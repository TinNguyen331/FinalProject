package com.kltn;

import com.kltn.Util.AuthorityName;
import com.kltn.entities.Authority;
import com.kltn.entities.User;
import com.kltn.repositories.AuthorityRepository;
import com.kltn.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
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

	/*
	@Bean
	FilterRegistrationBean corsFilter(
			@Value("${tagit.origin:http://localhost:4200}") String origin) {
		return new FilterRegistrationBean(new Filter() {
			@Override
			public void init(FilterConfig filterConfig) throws ServletException {

			}

			public void doFilter(ServletRequest req, ServletResponse res,
								 FilterChain chain) throws IOException, ServletException {
				HttpServletRequest request = (HttpServletRequest) req;
				HttpServletResponse response = (HttpServletResponse) res;
				String method = request.getMethod();
				// this origin value could just as easily have come from a database
				response.setHeader("Access-Control-Allow-Origin", origin);
				response.setHeader("Access-Control-Allow-Methods",
						"POST,GET,OPTIONS,PUT,DELETE");
				response.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
				response.setHeader("Access-Control-Allow-Credentials", "true");
				response.setHeader(
						"Access-Control-Allow-Headers",
						"Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
				if ("OPTIONS".equals(method)) {
					response.setStatus(HttpStatus.OK.value());
				}
				else {
					chain.doFilter(req, res);
				}
			}

			@Override
			public void destroy() {

			}


		});
	}*/


	@Autowired
	AuthorityRepository authorityRepository;
	@Autowired
	UserRepository userRepository;
	@Override
	public void run(String... strings) throws Exception {

		/*
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
		String hashedPassword = passwordEncoder.encode("admin");

		ad.setPassWord(hashedPassword);
		List<Authority> lst=new ArrayList<>();
		lst.add(admin);
		ad.setAuthorities(lst);
		ad.setFullName("admin");
		ad.setEnabled(true);
		ad.setLastPasswordResetDate(new Date());

		//Add user
		User ur=new User();
		ur.setUserName("user");
		BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
		String hashedPassword2 = passwordEncoder.encode("user");

		ur.setPassWord(hashedPassword2);
		List<Authority> lst2=new ArrayList<>();
		lst2.add(user);
		ur.setAuthorities(lst2);
		ur.setFullName("user");
		ur.setEnabled(true);
		ur.setLastPasswordResetDate(new Date());

		userRepository.save(ad);
		userRepository.save(ur);
		*/
	}
}

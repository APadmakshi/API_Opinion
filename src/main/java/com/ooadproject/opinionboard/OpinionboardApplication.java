package com.ooadproject.opinionboard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ooadproject.opinionboard.person.Person;
import com.ooadproject.opinionboard.person.Role;
import com.ooadproject.opinionboard.service.PersonServices;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
public class OpinionboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpinionboardApplication.class, args);
		
		System.out.println("first spring boot pgm");
		}
	CommandLineRunner run(PersonServices personService)
	{
		return args -> {
			personService.saveRole(new Role(null, "ROLE_User"));
			personService.addPerson(new Person(null, "test", "Test User", "test@ggtt.com", "123", LocalDate.now(), true, new ArrayList<>()));
			personService.addRoleToUser("test", "ROLE_User");
			
		};
	}
	@SuppressWarnings("deprecation")
	@Bean
	   public WebMvcConfigurer corsConfigurer() {
	      return new WebMvcConfigurerAdapter() {
	         @Override
	         public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/products").allowedOrigins("http://localhost:3000");
	         }
	      };
	   }
	 @Bean
	 PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }

}

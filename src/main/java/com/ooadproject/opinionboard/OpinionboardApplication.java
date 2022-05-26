package com.ooadproject.opinionboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class OpinionboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpinionboardApplication.class, args);
		
		System.out.println("first spring boot pgm");
		}

}

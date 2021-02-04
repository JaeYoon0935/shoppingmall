package com.shoppingmall.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ShoppingmallApplication extends SpringBootServletInitializer {
//public class ShoppingmallApplication extends SpringBootServletInitializer {
	
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(ShoppingmallApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(ShoppingmallApplication.class, args);		
		System.out.println("Hello World");
	}
}

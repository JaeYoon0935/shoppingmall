package com.shoppingmall.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {	
	   @Override
	    public void addCorsMappings(CorsRegistry registry) {
			System.out.println("체크 WebConfig");
	        registry.addMapping("/**") 
	            .allowedOrigins("*") // vue 서버의 도메인, 포트로 수정, 이 부분은 원래 해킹등의 문제로 ajax를 다른 서버에서 건드리는 것을 막아놨는데, localhost: 8080에 한해서는 허용하겠다는 뜻이다. 
	            .allowedMethods("*") //REST API의 get, post, patch, delete 등 모든 Methods를 허용하겠다는 뜻이다.
	        	.exposedHeaders("jwt-token");
	    }
}

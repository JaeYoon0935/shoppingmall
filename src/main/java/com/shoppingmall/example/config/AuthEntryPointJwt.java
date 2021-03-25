package com.shoppingmall.example.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


//이 클래스는 WebSecurityConfig.java 클래스에서 처음에 셋팅을 할때 
//에러가 발생하면 에러처리를 하기 위해 사용되는 클래스이다. 실제로 WebSecurityConfig.java클래스에 AuthEntryPointJwt 객체가 생성되어 있고,
//에러처리 부분에 AuthEntryPointJwt객체가 들어가 있음을 볼수 있다.

@Component 
//@Component 어노테이션: @Bean과 마찬가지로 빈을 등록하는 어노테이션이다.
//다만 개발자가 직접 만든 것을 빈으로 등록할때는 @Bean이 아니라 @Component를 사용한다.
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException{
		System.out.println("체크  AuthEntryPointJwt");
		logger.error("Unauthorized error:{}", authException.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
	}
	
	
}

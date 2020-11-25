package com.shoppingmall.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.shoppingmall.example.service.UserService;

@Configuration
//@Configuration: 1개이상의 @Bean을 등록한다면, 반드시 붙여줘야 하는 어노테이션이다.

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	//이 부분을 스프링 빈으로 등록해놓았기 때문에, 맨 아래에 configure에서 사용될수 있는 것이다.
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		//내가 사용하는 UserService클래스의 객체 userService를 주입해준다음 나의 password를 인코딩함.
		authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder()); 
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//스프링 시큐리티와 동일하게 환경설정하는 부분
		http.cors().and().csrf().disable()
			//권한 없음 에러 처리
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and() //AuthEntryPointJwt.java 클래스를 통한 에러처리 AuthEntryPointJwt.java 객체인 unauthorizedHandler를 주입함으로써 처리함.
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() 
			//해당 경로 모두 허용
			.authorizeRequests().antMatchers("/api/auth/**").permitAll()
			.antMatchers("/api/test/**").permitAll()
			.antMatchers("/api/admin/**").permitAll()
			//이외의 경로 인증처리 된 유저만 접근 가능
			.anyRequest().authenticated();
		
		//스프링 빈으로 등록해놓은 authenticationJwtTokenFilter() 메소드를 사용한다.
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	

}
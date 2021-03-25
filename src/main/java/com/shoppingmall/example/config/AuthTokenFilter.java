package com.shoppingmall.example.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shoppingmall.example.service.UserService;

import lombok.RequiredArgsConstructor;

//Jwt 토큰을 필터링 하기 위한 클래스이다.

@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserService userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	throws ServletException, IOException{
		try{
			System.out.println("체크 AuthTokenFilter");
			//받아온 jwt 토큰을 파싱해서 String에 집어 넣는다.
			String jwt = parseJwt(request);
			System.out.println(jwt);
			
			//토큰이 null이 아니고, 유효한 토큰이라면 아래 절차를 진행한다.
			if(jwt != null && jwtUtils.validateJwtToken(jwt)) {
				String username = jwtUtils.getUserNameFromJwtToken(jwt);
				
				UserDetails userDetails = userService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			} catch(Exception e) {
				logger.error("Cannot set user authentication: {}", e);
			}
			filterChain.doFilter(request, response);
		}
		
		//jwt토큰을 파싱한다. 바로 위에서 쓰이는 메서드이다.
		private String parseJwt(HttpServletRequest request) {
			
			String headerAuth = request.getHeader("Authorization");
			
			if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			//대부분 토큰 앞에 "Bearer "을 붙여줌, 그래서 "Bearer "을 제거하기 위해 substring(7)을 해줌.
				return headerAuth.substring(7, headerAuth.length());
			}
			return null;
		}
		
		
}
	
	
	



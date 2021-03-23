package com.shoppingmall.example.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.example.config.JwtUtils;
import com.shoppingmall.example.domain.User;
import com.shoppingmall.example.domain.UserInfo;
import com.shoppingmall.example.request.LoginRequest;
import com.shoppingmall.example.response.JwtResponse;
import com.shoppingmall.example.service.PointService;
import com.shoppingmall.example.service.UserService;

@CrossOrigin(origins= "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UserService userService;
	
	
	@Autowired
	PointService pointService;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest){
		
		logger.info("test" + loginRequest);
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
				
		User user = (User) authentication.getPrincipal();
		logger.info("dddd" + authentication.getPrincipal());
		List<String> roles = user.getAuthorities().stream()
			
				.map(item -> item.getAuthority())
								.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt,
												user.getUsername(),
												user.getName(),
												roles));
		
	}
	@PostMapping("/signup")
		public ResponseEntity<?> sinupUser(@Validated @RequestBody User user){
		
		int result = 0;
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmSS");	 
	    String date = dateFormat.format(cal.getTime());
		
		
		if(userService.duplicate(user) == null) {
			String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
			
			user.setUsername(user.getUsername());
			user.setName(user.getName());
			user.setPassword(encodedPassword);
			user.setPhone(user.getPhone());
			user.setAddress(user.getAddress());
			user.setEmail(user.getEmail());
			user.setAccountNonExpired(true);
			user.setEnabled(true);
			user.setAccountNonLocked(true);
			user.setCredentialsNonExpired(true);
			user.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));
			
			user.setDate(date); //회원가입 축하 포인트에 사용될 날짜
			
			
			result = userService.createUser(user);
			userService.createAuthority(user);
			
			//회원가입이 성공 되었을 때만, 회원가입 축하 포인트를 줌.
			if(result == 1) {
				// 회원가입 축하 포인트 주는 로직 작성하기.
				pointService.join(user);			
			}
			
			return new ResponseEntity<>("success", HttpStatus.OK);
		}else{
			return new ResponseEntity<>("duplicate", HttpStatus.OK);
		}
		
		
//		if(userService.duplicate(user) == null) {
//			String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
//			
//			user.setUsername(user.getUsername());
//			user.setName(user.getName());
//			user.setPassword(encodedPassword);
//			user.setPhone(user.getPhone());
//			user.setAddress(user.getAddress());
//			user.setEmail(user.getEmail());
//			user.setAccountNonExpired(true);
//			user.setEnabled(true);
//			user.setAccountNonLocked(true);
//			user.setCredentialsNonExpired(true);
//			user.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));
//			
//			result = userService.createUser(user);
//			userService.createAuthority(user);
//			
//			System.out.println("테스트 + " + result);
//			
//			return new ResponseEntity<>("success", HttpStatus.OK);
//		}else{
//			return new ResponseEntity<>("duplicate", HttpStatus.OK);
//		}
		
	}
	
	@PostMapping("/duplicate")
	public ResponseEntity<?> duplicate(@Validated @RequestBody User user){		

		System.out.println(user.getUsername());
	
		if(userService.duplicate(user) == null) {
			return new ResponseEntity<>("success", HttpStatus.OK);	
		}else{
			return new ResponseEntity<>("duplicate", HttpStatus.OK);
		}
		
	}
		
	@GetMapping("/unpackToken")
	public ResponseEntity<?> unpackToken(HttpServletRequest request) {
		String token = new String();
		token = request.getHeader("Authorization");
		
		if(StringUtils.hasText(token) && token.startsWith("Bearer " )) {
			token = token.substring(7, token.length());
		}
		String username = jwtUtils.getUserEmailFromToken(token);
		UserInfo user = userService.readUser_refresh(username);
		
		user.setAuthorities(userService.readAuthorities_refresh(username));
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}
	
	
}

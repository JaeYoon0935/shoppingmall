package com.shoppingmall.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.example.config.JwtUtils;
import com.shoppingmall.example.domain.Board;
import com.shoppingmall.example.domain.User;
import com.shoppingmall.example.domain.UserInfo;
import com.shoppingmall.example.response.JwtResponse;
import com.shoppingmall.example.response.JwtResponse1;
import com.shoppingmall.example.service.BoardService;
import com.shoppingmall.example.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/adminPage")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?>  AccessAdmin(HttpServletRequest request) {
		List<UserInfo> userList = userService.read_user_list();
		logger.info(userList.toString());
		  return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	
	//테스트 중인 부분
	@GetMapping("/userlist")
	public List<UserInfo> read_user(){
		List<UserInfo> userList = userService.shopping_readUser();
		return userList;
	}
	
		
}

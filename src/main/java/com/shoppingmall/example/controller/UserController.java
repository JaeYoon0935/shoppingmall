package com.shoppingmall.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

//사용자 페이지이므로 경로를 /user으로 사용함.
@RequestMapping("/api/user")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	
	
	
}

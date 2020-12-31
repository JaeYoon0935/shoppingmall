package com.shoppingmall.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.example.config.JwtUtils;
import com.shoppingmall.example.domain.Product;
import com.shoppingmall.example.service.BoardService;
import com.shoppingmall.example.service.CategoryService;
import com.shoppingmall.example.service.OrderService;
import com.shoppingmall.example.service.ProductService;
import com.shoppingmall.example.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

//사용자 화면, 일반 홈페이지이므로 경로를 /으로 사용함.
@RequestMapping("/api")
public class UserController {

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
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	OrderService orderService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	//상품수정하기
	@PostMapping("/product")
	public ResponseEntity<?> productUpdate(@Validated @RequestBody Product product){
		List<Product> product_one = productService.readProduct(product);
		return new ResponseEntity<>(product_one, HttpStatus.OK);
	}
	
}

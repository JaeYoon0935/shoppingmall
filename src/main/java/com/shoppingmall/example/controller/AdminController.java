package com.shoppingmall.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.example.config.JwtUtils;
import com.shoppingmall.example.domain.Category;
import com.shoppingmall.example.domain.Product;
import com.shoppingmall.example.domain.UserInfo;
import com.shoppingmall.example.service.BoardService;
import com.shoppingmall.example.service.CategoryService;
import com.shoppingmall.example.service.ProductService;
import com.shoppingmall.example.service.UserService;

//CrossOrigin을 사용하지 않으면 종종 8080(뷰)에 있는게 교류가 안될떄가 있어서 적어준다.
@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
//RestController와 일반 스테레오타입의 그냥 Controller와의 차이점
//리턴값을 보낼 때 단순 리턴값만 보내는 것이 아니라, HttpStatus.OK와 같은 상태값도 같이 보내야 한다는 것이다.
//이를 위해서 리턴시에 new ResponseEntity<>를 사용하게 된다.
//그리고 일반Controller을 사용할때는 ResponseBody어노테이션을 붙여줘야만, json값으로 리턴이 되어 프론트단으로 뿌릴수가 있었는데, 
//ResponseEntity<>의 경우 ResponseBody어노테이션을 붙여주지 않아도 자동으로 리턴값을 json객체로 만들어주므로 ResponseBody 어노테이션을 사용할 필요가 없다.


//관리자 페이지이므로 경로를 /admin으로 사용함.
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
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	
	@GetMapping("/adminPage")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?>  AccessAdmin(HttpServletRequest request) {
		List<UserInfo> userList = userService.read_user_list();
		logger.info(userList.toString());
		  return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	//회원정보 불러오기
	@GetMapping("/userlist")
	public ResponseEntity<?> read_user(){
		List<UserInfo> userList = userService.shopping_readUser();		
		//이 부분 나중에 ResponseEntity<>를 사용하도록 고치기.
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	//회원탈퇴 처리하기
	@PostMapping("/userdelete")
	public ResponseEntity<?> delete_user(@Validated @RequestBody UserInfo userinfo){
		userService.delete_user(userinfo.getUsername());		
		List<UserInfo> userList = userService.shopping_readUser();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	//회원정보 수정하기
	@PostMapping("/userupdate")
	public ResponseEntity<?> updateData(@Validated @RequestBody UserInfo userinfo){
		userService.updateUser(userinfo);
		List<UserInfo> userList = userService.shopping_readUser();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	
	//카테고리정보 불러오기
	@GetMapping("/categorylist")
	public ResponseEntity<?> readCategory(){
		List<Category> categoryList = categoryService.readCategory();
		return new ResponseEntity<>(categoryList, HttpStatus.OK);
	}
	
	//상품랭킹 불러오기
	@GetMapping("/ranking")
	public ResponseEntity<?> readRanking(){
		List<Product> ranking = productService.ranking();
		return new ResponseEntity<>(ranking, HttpStatus.OK);
	}	

	//카테고리 대분류명 불러오기
	@GetMapping("/categoryname")
	public ResponseEntity<?> categoryList(){
		List<Category> categoryname = categoryService.readCategory_name();
		return new ResponseEntity<>(categoryname, HttpStatus.OK);
	}	
	
	//판매랭킹페이지 - 카테고리 선택 시 해당 카테고리에 연관된 제품 불러오기
	@PostMapping("/categoryselect")
	public ResponseEntity<?> categoryselect(@Validated @RequestBody Category category){
		category.setName(category.getName());
	
		//1차적으로 분류명을 통해 카테고리 id를 찾아오는 부분 구현하기
		int findCg_id = categoryService.findCg_id(category.getName());
		System.out.println(findCg_id);
		category.setId(findCg_id);
		
		if(findCg_id == 0) {
			List<Product> lowCgData = productService.lowCgData_all();
			return new ResponseEntity<>(lowCgData, HttpStatus.OK);
		} else {
			List<Product> lowCgData = productService.lowCgData(category.getId());
			return new ResponseEntity<>(lowCgData, HttpStatus.OK);
	    }
	}	
	
	//상품정보 불러오기
	@GetMapping("/productlist")
	public ResponseEntity<?> read_product(){
		List<Product> productList = productService.readAllProduct();		
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}
	
	//상품정보 등록하기
	@PostMapping("/productcreate")
	public ResponseEntity<?> create_product(@Validated @RequestBody Product product){
		productService.createProduct(product);		
		productService.createImage(product);
		List<Product> productList = productService.readAllProduct();	
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}
	
	//상품삭제하기
	@PostMapping("/productdelete")
	public ResponseEntity<?> productDelete(@Validated @RequestBody Product product){
		productService.productDelete(product);
		productService.product_img_Delete(product);
		List<Product> productList = productService.readAllProduct();
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}
	
	//상품수정하기
	@PostMapping("/productupdate")
	public ResponseEntity<?> productUpdate(@Validated @RequestBody Product product){
		List<Product> product_one = productService.readProduct(product);
		return new ResponseEntity<>(product_one, HttpStatus.OK);
	}
	
	
//	//회원정보 수정하기
//		@PostMapping("/userupdate")
//		public ResponseEntity<?> updateData(@Validated @RequestBody UserInfo userinfo){
//			userService.updateUser(userinfo);
//			List<UserInfo> userList = userService.shopping_readUser();
//			return new ResponseEntity<>(userList, HttpStatus.OK);
//		}

	
	//상품정보수정하기
	@PostMapping("/productdataupdate")
	public ResponseEntity<?> productDataUpdate(@Validated @RequestBody Product product){
		productService.productDataUpdate(product);
		productService.productImgUpdate(product);
		List<Product> productList = productService.readAllProduct();
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}
	
	
	
}








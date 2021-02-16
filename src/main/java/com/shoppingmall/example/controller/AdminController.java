package com.shoppingmall.example.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingmall.example.config.JwtUtils;
import com.shoppingmall.example.domain.Category;
import com.shoppingmall.example.domain.Order;
import com.shoppingmall.example.domain.OrderDetail;
import com.shoppingmall.example.domain.Product;
import com.shoppingmall.example.domain.UserInfo;
import com.shoppingmall.example.service.BoardService;
import com.shoppingmall.example.service.CategoryService;
import com.shoppingmall.example.service.OrderService;
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
	
	@Autowired
	OrderService orderService;

	
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
	public ResponseEntity<?> update_user(@Validated @RequestBody UserInfo userinfo){
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
	
	//카테고리정보 불러오기
	@PostMapping("/categoryupdate")
	public ResponseEntity<?> categoryUpdate(@Validated @RequestBody Category category){
		categoryService.categoryUpdate(category);
		List<Category> categoryList = categoryService.readCategory();
		return new ResponseEntity<>(categoryList, HttpStatus.OK);
	}
	
	//카테고리정보 불러오기
	@PostMapping("/categoryadd")
	public ResponseEntity<?> categoryAdd(@Validated @RequestBody Category category){
		categoryService.categoryAdd(category);		
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
		category.setCg_id(findCg_id);
		
		if(findCg_id == 0) {
			List<Product> lowCgData = productService.lowCgData_all();
			return new ResponseEntity<>(lowCgData, HttpStatus.OK);
		} else {
			List<Product> lowCgData = productService.lowCgData(category.getCg_id());
			return new ResponseEntity<>(lowCgData, HttpStatus.OK);
	    }
	}	
	
	//상품정보 불러오기
	@GetMapping("/productlist")
	public ResponseEntity<?> read_product(){
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
	
	//상품정보 등록하기
	@RequestMapping(value="/productcreate", method=RequestMethod.POST)
	public ResponseEntity<?> upload(Product product)
	{
		MultipartFile multipartFile = product.getFile();
		System.out.println("product: "+ product);
		String filename = multipartFile.getOriginalFilename(); //서버로 부터 넘어오는 파일 전체이름
			
		//고유한 파일이름을 만들어 주기 위해 작업하는 부분
		Calendar cal = Calendar.getInstance()  ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmSS");	 
	    String time = dateFormat.format(cal.getTime());
	    
		
		product.setImage(filename); //
		product.setUnique(time);
		productService.createProduct(product);	
		productService.createImage(product);
	    
	 
		String path = "C:\\Users\\l4\\Documents\\Project\\shoppingmall\\src\\shoppingmall-vue\\src\\images\\";
		String thumbPath = path + "thumb\\";
		String ext = filename.substring(filename.lastIndexOf(".")+1);
		File file = new File(path + time + ".jpg");
		File thumbFile = new File(thumbPath + time + ".jpg");
		try {
			// 원본파일 저장
			InputStream input = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(input, file);
			
			// 썸네일 생성
			BufferedImage imageBuf = ImageIO.read(file);
			int fixWidth = 420;
			double ratio = imageBuf.getWidth() / (double)fixWidth;
			int thumbWidth = fixWidth;
			int thumbHeight = (int)(imageBuf.getHeight() / ratio);
			BufferedImage thumbImageBf = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_3BYTE_BGR);
			Graphics2D g = thumbImageBf.createGraphics();
			Image thumbImage = imageBuf.getScaledInstance(thumbWidth, thumbHeight, Image.SCALE_SMOOTH);
			g.drawImage(thumbImage, 0, 0, thumbWidth, thumbHeight, null);
			g.dispose();
			ImageIO.write(thumbImageBf, ext, thumbFile);
			
		} catch (IOException e) {
			FileUtils.deleteQuietly(file);
			e.printStackTrace();
		}
		
		List<Product> productList = productService.readAllProduct();	
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}
	

	//상품정보수정하기
	@RequestMapping(value="/productdataupdate", method=RequestMethod.POST)
	public ResponseEntity<?> productDataUpdate(Product product){
		
		//이미지는 업로드를 안할때
		if(product.getFile() == null) {
			productService.productDataUpdate(product);
		}
		
		//이미지도 업로드 할때
		else if(product.getFile() != null) {
			MultipartFile multipartFile = product.getFile();
			System.out.println("product: "+ product);
			String filename = multipartFile.getOriginalFilename();

			//고유한 파일이름을 만들어 주기 위해 작업하는 부분
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmSS");	 
		    String time = dateFormat.format(cal.getTime());
		    
			product.setImage(filename);
			product.setUnique(time);
		    
			productService.productDataUpdate(product);
			productService.productImgUpdate(product);
				
			String path = "C:\\Users\\l4\\Documents\\Project\\shoppingmall\\src\\shoppingmall-vue\\src\\images\\";
			String thumbPath = path + "thumb\\";
			String ext = filename.substring(filename.lastIndexOf(".")+1);
			File file = new File(path + time + ".jpg");
			File thumbFile = new File(thumbPath + time + ".jpg");
			
			try {
				// 원본파일 저장
				InputStream input = multipartFile.getInputStream();
				FileUtils.copyInputStreamToFile(input, file);
				
				// 썸네일 생성
				BufferedImage imageBuf = ImageIO.read(file);
				int fixWidth = 500;
				double ratio = imageBuf.getWidth() / (double)fixWidth;
				int thumbWidth = fixWidth;
				int thumbHeight = (int)(imageBuf.getHeight() / ratio);
				BufferedImage thumbImageBf = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_3BYTE_BGR);
				Graphics2D g = thumbImageBf.createGraphics();
				Image thumbImage = imageBuf.getScaledInstance(thumbWidth, thumbHeight, Image.SCALE_SMOOTH);
				g.drawImage(thumbImage, 0, 0, thumbWidth, thumbHeight, null);
				g.dispose();
				ImageIO.write(thumbImageBf, ext, thumbFile);
				
			} catch (IOException e) {
				FileUtils.deleteQuietly(file);
				e.printStackTrace();
			}
		}	
		
		
		List<Product> productList = productService.readAllProduct();
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}
	
	//상품수정하기
	@PostMapping("/productupdate")
	public ResponseEntity<?> productUpdate(@Validated @RequestBody Product product){
		List<Product> product_one = productService.readProduct(product);
		return new ResponseEntity<>(product_one, HttpStatus.OK);
	}
	
	//상품정보 불러오기
	@GetMapping("/orderlist")
	public ResponseEntity<?> read_order(){
		List<Order> orderList = orderService.readOrder();
		System.out.println(orderList);
		return new ResponseEntity<>(orderList, HttpStatus.OK);
	}
	
	//상품 상세정보 불러오기
	@PostMapping("/orderdetail")
	public ResponseEntity<?> read_orderDetail(@Validated @RequestBody Order order){
		System.out.println("order ID: "+ order.getId());
		Order orderDetail = orderService.readOrderDetails(order);
		System.out.println(orderDetail);
		return new ResponseEntity<>(orderDetail, HttpStatus.OK);
	}
	
	@PostMapping("/orderdetaildelete")
	public ResponseEntity<?> orderDetailDelete(@Validated @RequestBody OrderDetail orderdetail){
		
		//미리 o_id를 입력해둔다.
		Order order = new Order();
		order.setId(orderdetail.getO_id());
		
		//주문을 삭제한다.
		orderService.orderDetailDelete(orderdetail); 
		//o_id를 통해서 상세주문내역페이지를 다시 불러오도록 한다.	
		Order orderDetail = orderService.readOrderDetails(order);
		
		return new ResponseEntity<>(orderDetail, HttpStatus.OK);
	}
	
	@PostMapping("/orderdetailupdate")
	public ResponseEntity<?> orderDetailUpdate(@Validated @RequestBody OrderDetail orderdetail){
		//미리 o_id를 입력해둔다.
		Order order = new Order();
		order.setId(orderdetail.getO_id());
		
		//업데이트 한다.
		orderService.updateOrderDetail(orderdetail); 
		//o_id를 통해서 상세주문내역페이지를 다시 불러오도록 한다.	
		Order orderDetail = orderService.readOrderDetails(order);
		
		return new ResponseEntity<>(orderDetail, HttpStatus.OK);
	}	
	
	@PostMapping("/salesdata")
	public ResponseEntity<?> salesData(@Validated @RequestBody Order order){
		
		System.out.println("1");
		
//		System.out.println(order.getDateinfo());
		
		
		Order orderDetail = orderService.readOrderDetails(order);	
		return new ResponseEntity<>(orderDetail, HttpStatus.OK);
	}
	
}








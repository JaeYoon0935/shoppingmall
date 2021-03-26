package com.shoppingmall.example.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.shoppingmall.example.domain.DateInfo;
import com.shoppingmall.example.domain.Order;
import com.shoppingmall.example.domain.OrderDetail;
import com.shoppingmall.example.domain.Point;
import com.shoppingmall.example.domain.Product;
import com.shoppingmall.example.domain.UserInfo;
import com.shoppingmall.example.service.BoardService;
import com.shoppingmall.example.service.CategoryService;
import com.shoppingmall.example.service.OrderService;
import com.shoppingmall.example.service.PointService;
import com.shoppingmall.example.service.ProductService;
import com.shoppingmall.example.service.UserService;

//CrossOrigin을 사용하지 않으면 종종 8080(뷰)에 있는게 교류가 안될떄가 있어서 적어준다.
@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
//RestController와 일반 스테레오타입의 그냥 Controller와의 차이점
//리턴값을 보낼 때 단순 리턴값만 보내는 것이 아니라, HttpStatus.OK와 같은 상태값도 같이 보내야 한다는 것이다.
//이를 위해서 리턴시에 new ResponseEntity<>를 사용하게 된다.
//그리고 일반 Controller을 사용할때는 ResponseBody어노테이션을 붙여줘야만, json값으로 리턴이 되어 프론트단으로 뿌릴수가 있었는데, 
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

	@Autowired
	PointService pointService;
	
//  이 부분은 사용안하므로 주석처리함.
//	@GetMapping("/adminPage")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	public ResponseEntity<?>  AccessAdmin(HttpServletRequest request) {
//		List<UserInfo> userList = userService.read_user_list();
//		logger.info(userList.toString());
//		  return new ResponseEntity<>(userList, HttpStatus.OK);
//	}
	
	//회원정보 불러오기
	//단순 조회기능은 모든 사용자에게 권한을 부여해준다.
	@GetMapping("/userlist")
	public ResponseEntity<?> read_user(){
		List<UserInfo> userList = userService.shopping_readUser();		
		//이 부분 나중에 ResponseEntity<>를 사용하도록 고치기.
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	//회원탈퇴 처리하기
	//이 로직으로 들어왔을때 권한이 없으면, alert로 권한이 없습니다를 띄워주도록 하기.
	@PostMapping("/userdelete")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> delete_user(@Validated @RequestBody UserInfo userinfo){
		userService.delete_user(userinfo.getUsername());		
		System.out.println("체크");
		List<UserInfo> userList = userService.shopping_readUser();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	//회원정보 수정하기
	//이 로직으로 들어왔을때 권한이 없으면, alert로 권한이 없습니다를 띄워주도록 하기.
	@PostMapping("/userupdate")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> update_user(@Validated @RequestBody UserInfo userinfo){
		System.out.println("체크");
		userService.updateUser(userinfo);
		List<UserInfo> userList = userService.shopping_readUser();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	//포인트정보 불러오기
	@GetMapping("/point")
	public ResponseEntity<?> point(){
		List<Point> point = pointService.read_point();
		return new ResponseEntity<>(point, HttpStatus.OK);
	}
	
	//포인트 내역추가
	@PostMapping("/pointadd")
	public ResponseEntity<?> pointadd(@Validated @RequestBody Point point){
		
		//회원 아이디가 없는 경우 에러를 반환한다.
		if(pointService.checkId(point) == null) {
			return new ResponseEntity<>("error", HttpStatus.OK);
		//회원 아이디가 있는 경우	
		}else {
			Point sum = new Point();
			sum = pointService.sumPointById(point);
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");	 
		    String date = dateFormat.format(cal.getTime());
			
			int temp_total = Integer.parseInt(sum.getTotal_point()) + Integer.parseInt(point.getPoint()); 
			String total = Integer.toString(temp_total);
					
			point.setDate(date);
			point.setTotal_point(total);
			point.setName(sum.getName());

			pointService.createPoint(point);
			
			return new ResponseEntity<>("success", HttpStatus.OK);			
		}
	}
	
	//카테고리정보 불러오기
	@GetMapping("/categorylist")
	public ResponseEntity<?> readCategory(){
		List<Category> categoryList = categoryService.readCategory();
		return new ResponseEntity<>(categoryList, HttpStatus.OK);
	}
	
	//분류명수정하기
	@PostMapping("/categoryupdate")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> categoryUpdate(@Validated @RequestBody Category category){
		categoryService.categoryUpdate(category);
		List<Category> categoryList = categoryService.readCategory();
		return new ResponseEntity<>(categoryList, HttpStatus.OK);
	}
	
	//카테고리정보 불러오기
	@PostMapping("/categoryadd")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> categoryAdd(@Validated @RequestBody Category category){
		System.out.println(category.getName()+"wjflwejfkljwlekfjkl");
		categoryService.categoryAdd(category);		
		List<Category> categoryList = categoryService.readCategory();
		return new ResponseEntity<>(categoryList, HttpStatus.OK);
	}
	
	//상품랭킹 불러오기
	@GetMapping("/ranking")
	public ResponseEntity<?> readRanking(){
		List<Product> ranking = productService.lowCgData_all();
		return new ResponseEntity<>(ranking, HttpStatus.OK);
	}	

	//카테고리 대분류명 불러오기
	@GetMapping("/categoryname")
	public ResponseEntity<?> categoryList(){
		List<Category> categoryname = categoryService.readCategory_name();
		return new ResponseEntity<>(categoryname, HttpStatus.OK);
	}	
	
	//판매랭킹페이지 - 분류명을 통해서 판매랭킹을 불러오는 경우
	@PostMapping("/categoryselect")
	public ResponseEntity<?> categoryselect(@Validated @RequestBody Category category){
		
		System.out.println(category.getDate1());
		System.out.println(category.getDate2());
		category.setName(category.getName());
		
		if(category.getDate1().length() < 2 || category.getDate2().length() < 2)  //날짜 정보가 없는 경우, 전체기간에서 찾아옴. (null인 경우)
		{
			//1차적으로 분류명을 통해 카테고리 id를 찾아오는 부분 구현하기
			int findCg_id = categoryService.findCg_id(category.getName());
			System.out.println("날짜정보 없음");
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
		else //날짜정보가 있는 경우
		{		
			//0이면 다불러와야하는데, 0이면 하나도 못불러옴 이거 고쳐주기
			int findCg_id = categoryService.findCg_id(category.getName());
			System.out.println(findCg_id);
			category.setCg_id(findCg_id);
			
			if(findCg_id == 0) {
				List<Product> lowCgData = productService.CgData_AllTime(category);
				return new ResponseEntity<>(lowCgData, HttpStatus.OK);	
			}else {
				List<Product> lowCgData = productService.CgData_Date(category);
				return new ResponseEntity<>(lowCgData, HttpStatus.OK);	
			}
		}		
	}	
	
	//판매랭킹 페이지 - 기간을 고려해서 판매랭킹을 불러오는 경우.
	@PostMapping("/salesbytime")
	public ResponseEntity<?> salesbytime(@Validated @RequestBody Category category){
		
		List<Product> salesData = null;
		
		//넘어오는 정보 체크
		System.out.println(category.getDate1());
		System.out.println(category.getDate2());
		System.out.println(category.getName());
		
		int findCg_id = categoryService.findCg_id(category.getName());
		category.setCg_id(findCg_id);

		if(category.getName().equals("전체")) {
			System.out.println("전체 조회");
			salesData = productService.salesbytime(category);
		}
		else {
			salesData = productService.CgData_Date(category);
		}
	
		return new ResponseEntity<>(salesData, HttpStatus.OK);
	}
	
	//상품정보 불러오기
	@GetMapping("/productlist")
	public ResponseEntity<?> read_product(){
		List<Product> productList = productService.readAllProduct();		
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

	//상품삭제하기
	@PostMapping("/productdelete")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmSS");	 
	    String time = dateFormat.format(cal.getTime());
	    
		
		product.setImage(filename);
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
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
		System.out.println(orderDetail.toString());
		return new ResponseEntity<>(orderDetail, HttpStatus.OK);
	}
	
	//상세정보 상품삭제
	@PostMapping("/orderdetaildelete")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	
	//상세정보 상품수정
	@PostMapping("/orderdetailupdate")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	public ResponseEntity<?> salesData(@Validated @RequestBody DateInfo dateinfo){
		
		//넘어오는 날짜 체크
		System.out.println(dateinfo.getDateinfo().date1);
		System.out.println(dateinfo.getDateinfo().date2);		
			
		//월간 매출 월 계산하기 위한 변수 
		List<Integer> thirty_one = new ArrayList<Integer>();
		List<Integer> thirty = new ArrayList<Integer>();
		thirty_one.add(1);
		thirty_one.add(3);
		thirty_one.add(5);
		thirty_one.add(7);
		thirty_one.add(8);
		thirty_one.add(10);
		thirty_one.add(12);
		thirty.add(4);
		thirty.add(6);
		thirty.add(9);
		thirty.add(11);
		
		List<Order> salesData = null;
		int len1, len2 = 0;
		
		if(dateinfo.getDateinfo().date1 != null) {
			len1 = dateinfo.getDateinfo().date1.length();
		}else {
			len1 = 0;
		}
		
		if(dateinfo.getDateinfo().date2 != null) {
			len2 = dateinfo.getDateinfo().date2.length();
		}else {
			len2 = 0;
		}
		
		try {		
			//일일 매출
			if(len2 == 0) {
				//readSales_oneday 말고 나머지는 mapper에서 쓰이는 쿼리가 같기 때문에 나중에 리팩터링 해주기
				salesData = orderService.readSales_oneday(dateinfo.getDateinfo());
			}
			//일간매출
			else if(len1 == 10 && len2 == 10) {
				//readSales_oneday 말고 나머지는 mapper에서 쓰이는 쿼리가 같기 때문에 나중에 리팩터링 해주기
				salesData = orderService.readSales_days(dateinfo.getDateinfo());	
			}
			//월간매출
			else if(len1 == 7 && len2 == 7) {
				dateinfo.getDateinfo().date1 = dateinfo.getDateinfo().date1 + "-01" ;
				
				String date2_str = dateinfo.getDateinfo().date2.substring(dateinfo.getDateinfo().date2.length()-2, dateinfo.getDateinfo().date2.length());		
				int date2_int = Integer.parseInt(date2_str);
				
				System.out.println("31" + thirty_one.contains(date2_int));
				System.out.println("30" + thirty.contains(date2_int));

				if(thirty_one.contains(date2_int)) {
					System.out.println("31");
					dateinfo.getDateinfo().date2 = (dateinfo.getDateinfo().date2 + "-31") ;
				}
				else if(thirty.contains(date2_int)){
					System.out.println("30");
					dateinfo.getDateinfo().date2 = (dateinfo.getDateinfo().date2 + "-30") ;
				}
				else {
					System.out.println("28");
					dateinfo.getDateinfo().date2 = (dateinfo.getDateinfo().date2 + "-28") ;
				}
					
				System.out.println(dateinfo.getDateinfo().date1);
				System.out.println(dateinfo.getDateinfo().date2);
				//readSales_oneday 말고 나머지는 mapper에서 쓰이는 쿼리가 같기 때문에 나중에 리팩터링 해주기
				salesData = orderService.readSales_month(dateinfo.getDateinfo());
			}
			
			//연간매출
			else if(len1 == 4 && len2 == 4) {
				
				dateinfo.getDateinfo().date1 = dateinfo.getDateinfo().date1 + "-01-01";
				dateinfo.getDateinfo().date2 = dateinfo.getDateinfo().date2 + "-12-31";
				
				System.out.println(dateinfo.getDateinfo().date1);
				System.out.println(dateinfo.getDateinfo().date2);
				//readSales_oneday 말고 나머지는 mapper에서 쓰이는 쿼리가 같기 때문에 나중에 리팩터링 해주기
				salesData = orderService.readSales_year(dateinfo.getDateinfo());
			}	
			
		} catch(Exception e){
			logger.error("매출통계값 확인 에러", e);
		} 
		
		if(salesData.isEmpty() == true) {
			return new ResponseEntity<>("empty", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(salesData, HttpStatus.OK);	
		}
		
	}
	
}

package com.shoppingmall.example.domain;

import java.util.List;

public class Order {

	private String state;//주문상태 o_state 
	private int id; //주문정보 o_id
	private int total_price; //주문정보 o_total_price
	private int order_price; //주문정보 o_order_price
	private String date; // 주문정보 o_date
	private String user_id; //주문정보 user_id (주문한 사람의 아이디)
	private List<String> productList;  //상품정보 o_id에 해당하는 od_product들을 담고있을 리스트 
	
	
	private DateInfo dateinfo; //주문정보를 받을 객체
	
	
	private String name; //사용자정보 user_name (주문한 사람의 이름)
	private String phone; //사용자정보 user_phone
	private String address; //사용자정보 user_address
	
	
	private String code; //상품코드 p_id
	private String product; //상품명 p_name
	private String price; //상품정보 p_price
	private String image; //상품정보 pi_image , 너무 복잡해져서 우선은 임시로 od_image로 대체함.
	
	
	private List<OrderDetail> orderdetail;
	private UserInfo user;
	private List<Product> product_map;
	private List<Product_img> product_img_map;
	
	
	public int getOrder_price() {
		return order_price;
	}
	public void setOrder_price(int order_price) {
		this.order_price = order_price;
	}
	public List<String> getProductList() {
		return productList;
	}
	public void setProductList(List<String> productList) {
		this.productList = productList;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public List<OrderDetail> getOrderdetail() {
		return orderdetail;
	}
	public void setOrderdetail(List<OrderDetail> orderdetail) {
		this.orderdetail = orderdetail;
	}
	public List<Product> getProduct_map() {
		return product_map;
	}
	public void setProduct_map(List<Product> product_map) {
		this.product_map = product_map;
	}

	public List<Product_img> getProduct_img_map() {
		return product_img_map;
	}
	public void setProduct_img_map(List<Product_img> product_img_map) {
		this.product_img_map = product_img_map;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public DateInfo getDateinfo() {
		return dateinfo;
	}
	public void setDateinfo(DateInfo dateinfo) {
		this.dateinfo = dateinfo;
	}
	@Override
	public String toString() {
		return "Order [state=" + state + ", id=" + id + ", total_price=" + total_price + ", order_price=" + order_price
				+ ", date=" + date + ", user_id=" + user_id + ", productList=" + productList + ", dateinfo=" + dateinfo
				+ ", name=" + name + ", phone=" + phone + ", address=" + address + ", code=" + code + ", product="
				+ product + ", price=" + price + ", image=" + image + ", orderdetail=" + orderdetail + ", user=" + user
				+ ", product_map=" + product_map + ", product_img_map=" + product_img_map + "]";
	}
	
	

}

package com.shoppingmall.example.domain;

import org.springframework.web.multipart.MultipartFile;

public class Product {

	private int id; //상품코드
	private String name; //상품명
	private String price; // 상품가격
	private String quantity; //상품재고
	private String order_count; //상품 누적주문수
	private String rank; //순위
	private String category; //카테고리
	private int views; // 조회수
	private String text;
	MultipartFile file;
	
	// 이미지 부분은 실제 테이블 칼럼상에는 없는 부분이나 편의상 product vo를 사용해서 작업함.
	private String image; //이미지, 실제 product테이블 상에는 없지만 임의로 사용
	private String unique; //고유한 파일명
	
	
	public String getUnique() {
		return unique;
	}
	public void setUnique(String unique) {
		this.unique = unique;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getOrder_count() {
		return order_count;
	}
	public void setOrder_count(String order_count) {
		this.order_count = order_count;
	}
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", order_count="
				+ order_count + ", rank=" + rank + ", category=" + category + ", image=" + image + ", views=" + views
				+ ", text=" + text + ", file=" + file + "]";
	}

}

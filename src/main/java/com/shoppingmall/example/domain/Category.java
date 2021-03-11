package com.shoppingmall.example.domain;

public class Category {
	
	private int cg_id; //상품코드
	private String name; //카테고리 목록이름
	private String product_count; //상품수
	
	//날짜정보가 있을 경우 날짜정보와 분류명을 함께 사용해서 데이터를 불러오기 위한 변수
	public String date1;
	public String date2;
	
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
		this.date2 = date2;
	}
	public int getCg_id() {
		return cg_id;
	}
	public void setCg_id(int cg_id) {
		this.cg_id = cg_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getProduct_count() {
		return product_count;
	}
	public void setProduct_count(String product_count) {
		this.product_count = product_count;
	}
	
	@Override
	public String toString() {
		return "Category [cg_id=" + cg_id + ", name=" + name + ", product_count=" + product_count + "]";
	}
	
}

package com.shoppingmall.example.domain;

public class Category {
	
	private int cg_id; //상품코드
	private String name; //카테고리 목록이름
	private String product_count; //상품수
	

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

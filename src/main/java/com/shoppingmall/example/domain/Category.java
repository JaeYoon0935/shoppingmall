package com.shoppingmall.example.domain;

public class Category {
	
	private int id; //��ǰ�ڵ�
	private String name; //ī�װ��� ����̸�
	private String product_count; //��ǰ��
	
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
	public String getProduct_count() {
		return product_count;
	}
	public void setProduct_count(String product_count) {
		this.product_count = product_count;
	}
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", product_count=" + product_count + "]";
	}
	
}
package com.shoppingmall.example.domain;

public class Product {

	private int id; // ��ǰ�ڵ�
	private String name; //��ǰ��
	private String quantity; //��ǰ���
	private String order_count; //��ǰ �����ֹ���
	private String rank; //����
	
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
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", quantity=" + quantity + ", order_count=" + order_count + "]";
	}

}
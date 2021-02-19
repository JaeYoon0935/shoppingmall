package com.shoppingmall.example.domain;

public class OrderDetail {
	
	private int od_id; //od_id
	private String product; //p_name
	private String price; //od_price
	private String count; //od_count
	private String image; //od_image
	private String unique; //pi_unique
	
	private int p_id;
	private int o_id;
	
	
	public String getUnique() {
		return unique;
	}
	public void setUnique(String unique) {
		this.unique = unique;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getOd_id() {
		return od_id;
	}
	public void setOd_id(int od_id) {
		this.od_id = od_id;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getO_id() {
		return o_id;
	}
	public void setO_id(int o_id) {
		this.o_id = o_id;
	}



}

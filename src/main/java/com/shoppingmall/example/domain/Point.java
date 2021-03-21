package com.shoppingmall.example.domain;

public class Point {
	
	public int po_id;
	public String username; // user_id
	public String name; //user_name
	public String content; //po_content
	public String point; //po_point
	public String total_point;  //po_total_point
	public String date; // po_date
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getPo_id() {
		return po_id;
	}
	public void setPo_id(int po_id) {
		this.po_id = po_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getTotal_point() {
		return total_point;
	}
	public void setTotal_point(String total_point) {
		this.total_point = total_point;
	}
	
}
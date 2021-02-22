package com.shoppingmall.example.domain;

import org.springframework.web.multipart.MultipartFile;

public class Product_img {

	private int pi_id;
	private String image;
	private MultipartFile multipartFile;
	private int p_id;
	private String unique; //고유한 파일이름
	
	public String getUnique() {
		return unique;
	}
	public void setUnique(String unique) {
		this.unique = unique;
	}
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	public int getPi_id() {
		return pi_id;
	}
	public void setPi_id(int pi_id) {
		this.pi_id = pi_id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	
}

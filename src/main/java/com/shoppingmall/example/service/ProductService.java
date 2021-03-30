package com.shoppingmall.example.service;

import java.util.List;

import com.shoppingmall.example.domain.Category;
import com.shoppingmall.example.domain.DateInfo;
import com.shoppingmall.example.domain.Product;

public interface ProductService {

	public List<Product> lowCgData(int id);
	
	public List<Product> lowCgData_all();
	
	public List<Product> CgData_Date(Category category);
	
	public List<Product> CgData_AllTime(Category category);
	
	public List<Product> salesbytime(DateInfo dateinfo);
	
	public List<Product> salesbytime(Category category);

	public List<Product> readAllProduct();
	
	public List<Product> readProduct(Product product);
	
	public void createProduct(Product product);
	
	public void createImage(Product product);
	
	public void productDelete(Product product);
	
	public void product_img_Delete(Product product);
	
	public void productDataUpdate(Product product);
	
	public void productImgUpdate(Product product);
	
	//홈화면에 상품랭킹에 따른 이미지
	public List<Product> imageByRank();
	
	//홈화면에 거실등 이미지
	public List<Product> imageLiving();

	//홈화면에 방등 이미지
	public List<Product> imageRoom();
	
	//홈화면에 전기지료 이미지
    public List<Product> imageMaterials();
}


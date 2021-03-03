package com.shoppingmall.example.service;

import java.util.List;

import com.shoppingmall.example.domain.DateInfo;
import com.shoppingmall.example.domain.Product;

public interface ProductService {

	public List<Product> lowCgData(int id);
	
	public List<Product> lowCgData_all();
	
	public List<Product> salesbytime(DateInfo dateinfo);
	
	public List<Product> readAllProduct();
	
	public List<Product> readProduct(Product product);
	
	public void createProduct(Product product);
	
	public void createImage(Product product);
	
	public void productDelete(Product product);
	
	public void product_img_Delete(Product product);
	
	public void productDataUpdate(Product product);
	
	public void productImgUpdate(Product product);

}


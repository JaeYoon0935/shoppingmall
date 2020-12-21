package com.shoppingmall.example.service;

import java.util.List;

import com.shoppingmall.example.domain.Product;
import com.shoppingmall.example.domain.Product_img;

public interface ProductService {
	
	public List<Product> ranking();
	
	public List<Product> lowCgData(int id);
	
	public List<Product> lowCgData_all();
	
	public List<Product> readProduct();
	
	public void createProduct(Product product);
	
	public void createImage(Product product);
}


package com.shoppingmall.example.service;

import java.util.List;
import com.shoppingmall.example.domain.Product;

public interface ProductService {
	
	public List<Product> ranking();
	
	public List<Product> lowCgData();
	
}

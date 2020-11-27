package com.shoppingmall.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.example.domain.Product;
import com.shoppingmall.example.mapper.ProductMapper;

@Service("ProductServiceImpl")
public class ProductServiceImpl {

	@Autowired 
	ProductMapper productmapper; 
	
	
	public List<Product> ranking(){
		return productmapper.ranking(); 
	}
	
}

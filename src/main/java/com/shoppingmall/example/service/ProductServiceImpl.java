package com.shoppingmall.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.example.domain.Product;
import com.shoppingmall.example.mapper.ProductMapper;

@Service("ProductServiceImpl")
public class ProductServiceImpl implements ProductService{

	@Autowired 
	ProductMapper productmapper; 
	
	@Override
	public List<Product> ranking(){
		return productmapper.ranking(); 
	}
	
	@Override
	public List<Product> lowCgData(){
		return productmapper.lowCgData();
	};
	
	
}



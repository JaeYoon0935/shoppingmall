package com.shoppingmall.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shoppingmall.example.domain.Product;
import com.shoppingmall.example.domain.Product_img;

@Mapper
public interface ProductMapper {
	
	public List<Product> ranking();

	public List<Product> lowCgData(int id);
	
	public List<Product> lowCgData_all();
	
	public List<Product> readProduct();
	
	public void createProduct(Product product);

	public void createImage(Product product);
}

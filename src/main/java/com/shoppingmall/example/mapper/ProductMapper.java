package com.shoppingmall.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shoppingmall.example.domain.Product;

@Mapper
public interface ProductMapper {
	
	public List<Product> ranking();

	public List<Product> lowCgData(int id);
	
}

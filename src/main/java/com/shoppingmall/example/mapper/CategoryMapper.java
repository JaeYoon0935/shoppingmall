package com.shoppingmall.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shoppingmall.example.domain.Category;

@Mapper
public interface CategoryMapper {

	public List<Category> readCategory();
	
	public List<Category> readCategory_name();
	
	public int findCg_id(String name);
}

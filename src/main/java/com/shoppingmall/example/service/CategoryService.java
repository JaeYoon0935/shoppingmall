package com.shoppingmall.example.service;

import java.util.List;

import com.shoppingmall.example.domain.Category;

public interface CategoryService {

	public List<Category> readCategory();
	
	public List<Category> readCategory_name();
	
	public List<Category> findCg_id();
}

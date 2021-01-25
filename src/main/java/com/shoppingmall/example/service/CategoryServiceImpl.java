package com.shoppingmall.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.example.domain.Category;
import com.shoppingmall.example.mapper.CategoryMapper;


@Service("CategoryServiceImpl")
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryMapper categorymapper;
	
	@Override
	public List<Category> readCategory() {
		return categorymapper.readCategory();
	};

	@Override
	public List<Category> readCategory_name(){
		return categorymapper.readCategory_name();
	};
	
	@Override
	public int findCg_id(String name){
		return categorymapper.findCg_id(name);
	};
	
	@Override
	public void categoryUpdate(Category category) {
		categorymapper.categoryUpdate(category);
	};
}

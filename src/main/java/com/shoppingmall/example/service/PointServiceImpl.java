package com.shoppingmall.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shoppingmall.example.mapper.PointMapper;

@Service("PointServiceImpl")
public class PointServiceImpl {
	
	@Autowired
	PointMapper pointmapper;

}

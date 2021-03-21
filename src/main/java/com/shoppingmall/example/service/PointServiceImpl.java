package com.shoppingmall.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.example.domain.Point;
import com.shoppingmall.example.mapper.PointMapper;

@Service("PointServiceImpl")
public class PointServiceImpl implements PointService {
	
	@Autowired
	PointMapper pointmapper;

	@Override
	public List<Point> read_point() {
		return pointmapper.read_point();
	}

}

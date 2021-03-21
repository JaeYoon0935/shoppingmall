package com.shoppingmall.example.service;

import java.util.List;

import com.shoppingmall.example.domain.Point;

public interface PointService {

	public List<Point> read_point();
	
	public Point sumPointById(Point point);
	
	public void createPoint(Point point);
	
	public Point checkId(Point point);
}

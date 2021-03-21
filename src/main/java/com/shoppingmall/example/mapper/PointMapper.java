package com.shoppingmall.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shoppingmall.example.domain.Point;

@Mapper
public interface PointMapper {

	public List<Point> read_point();
}

package com.shoppingmall.example.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shoppingmall.example.domain.Order;

@Mapper
public interface OrderMapper {

	public List<Order> readOrder();

	public ArrayList<Order> readOrderDetails(Order order);
}

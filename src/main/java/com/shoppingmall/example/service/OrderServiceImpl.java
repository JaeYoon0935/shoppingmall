package com.shoppingmall.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.example.domain.Order;
import com.shoppingmall.example.mapper.OrderMapper;

@Service("OrderServiceImpl")
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderMapper ordermapper;
	
	@Override
	public List<Order> readOrder(){
		return ordermapper.readOrder();
	};
	
}

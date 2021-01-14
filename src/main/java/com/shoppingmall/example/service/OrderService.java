package com.shoppingmall.example.service;

import java.util.ArrayList;
import java.util.List;

import com.shoppingmall.example.domain.Order;

public interface OrderService {
	
	public List<Order> readOrder();
	
	public Order readOrderDetails(Order order);

	
	
}

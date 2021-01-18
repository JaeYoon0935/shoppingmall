package com.shoppingmall.example.service;

import java.util.List;

import com.shoppingmall.example.domain.Order;
import com.shoppingmall.example.domain.OrderDetail;

public interface OrderService {
	
	public List<Order> readOrder();
	
	public Order readOrderDetails(Order order);

	public void orderDetailDelete(OrderDetail orderdetail);
	
}

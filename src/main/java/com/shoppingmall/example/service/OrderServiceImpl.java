package com.shoppingmall.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.example.domain.DateInfo;
import com.shoppingmall.example.domain.Order;
import com.shoppingmall.example.domain.OrderDetail;
import com.shoppingmall.example.mapper.OrderMapper;

@Service("OrderServiceImpl")
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderMapper ordermapper;
	
	@Override
	public List<Order> readOrder(){
		return ordermapper.readOrder();
	}
	
	@Override
	public Order readOrderDetails(Order order){
		return ordermapper.readOrderDetails(order);
	}

	@Override
	public void orderDetailDelete(OrderDetail orderdetail) {
		ordermapper.orderDetailDelete(orderdetail);
	}
	
	@Override
	public void updateOrderDetail(OrderDetail orderdetail) {
		ordermapper.updateOrderDetail(orderdetail);
	}
	
	@Override
	public List<Order> readSales_days(DateInfo dateinfo) {
		return ordermapper.readSales_days(dateinfo);
	}
	
	@Override
	public List<Order> readSales_oneday(DateInfo dateinfo){
		return ordermapper.readSales_oneday(dateinfo);
	}
	
	@Override
	public List<Order> readSales_month(DateInfo dateinfo){
		return ordermapper.readSales_month(dateinfo);
	}
	
	@Override
	public List<Order> readSales_year(DateInfo dateinfo){
		return ordermapper.readSales_year(dateinfo);
	}
	
}

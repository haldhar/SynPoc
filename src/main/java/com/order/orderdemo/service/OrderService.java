package com.order.orderdemo.service;

import com.order.orderdemo.dao.entity.Order;

public interface OrderService {

	Iterable<Order> getAllOrders();

	Order create(Order order);

	void update(Order order);
}
package com.order.orderdemo.service.impl;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.orderdemo.dao.entity.Order;
import com.order.orderdemo.service.OrderRepository;
import com.order.orderdemo.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Override
	public Iterable<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order create(Order order) {
		order.setDateCreated(LocalDate.now());

		return orderRepository.save(order);
	}

	@Override
	public void update(Order order) {
		orderRepository.save(order);
	}
}
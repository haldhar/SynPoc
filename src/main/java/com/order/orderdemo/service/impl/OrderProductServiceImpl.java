package com.order.orderdemo.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.orderdemo.dao.entity.OrderProduct;
import com.order.orderdemo.service.OrderProductRepository;
import com.order.orderdemo.service.OrderProductService;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

	@Autowired
	OrderProductRepository orderProductRepository;

	@Override
	public OrderProduct create(OrderProduct orderProduct) {
		return orderProductRepository.save(orderProduct);
	}
}
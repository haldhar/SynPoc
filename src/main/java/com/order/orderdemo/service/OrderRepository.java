package com.order.orderdemo.service;

import org.springframework.data.repository.CrudRepository;

import com.order.orderdemo.dao.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}

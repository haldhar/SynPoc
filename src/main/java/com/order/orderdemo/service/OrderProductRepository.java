package com.order.orderdemo.service;

import org.springframework.data.repository.CrudRepository;

import com.order.orderdemo.dao.entity.OrderProduct;
import com.order.orderdemo.dao.entity.OrderProductPK;

public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {
}
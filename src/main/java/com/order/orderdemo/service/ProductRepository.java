package com.order.orderdemo.service;

import org.springframework.data.repository.CrudRepository;

import com.order.orderdemo.dao.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
}

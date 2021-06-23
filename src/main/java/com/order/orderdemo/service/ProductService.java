package com.order.orderdemo.service;

import com.order.orderdemo.dao.entity.Product;

public interface ProductService {

	Iterable<Product> getAllProducts();

	Product getProduct(long id);

	Product save(Product product);
}

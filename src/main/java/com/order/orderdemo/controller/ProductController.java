package com.order.orderdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.order.orderdemo.dao.entity.Product;
import com.order.orderdemo.service.ProductService;

@CrossOrigin
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(path = "/products")
    public Iterable<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(path = "/product/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }
}

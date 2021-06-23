package com.order.orderdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.order.orderdemo.dao.entity.Product;
import com.order.orderdemo.service.ProductService;


@Configuration
@SpringBootApplication
public class OrderDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ProductService productService) {
        return args -> {
            productService.save(new Product(1L, "TV Set", 300.00));
            productService.save(new Product(2L, "Game Console", 200.00));
            productService.save(new Product(3L, "Sofa", 100.00));
            productService.save(new Product(4L, "Icecream", 5.00));
            productService.save(new Product(5L, "Beer", 3.00));
            productService.save(new Product(6L, "Phone", 500.00));
            productService.save(new Product(7L, "Watch", 30.00));
        };
    }

}

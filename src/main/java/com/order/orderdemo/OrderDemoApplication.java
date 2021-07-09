package com.order.orderdemo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.order.orderdemo.dao.UserDetailsRepository;
import com.order.orderdemo.dao.entity.Product;
import com.order.orderdemo.dao.entity.Role;
import com.order.orderdemo.dao.entity.User;
import com.order.orderdemo.service.ProductService;
import com.order.orderdemo.service.RoleRepository;

@Configuration
@SpringBootApplication
public class OrderDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ProductService productService, RoleRepository roleRepository, UserDetailsRepository userDetailsRepository) {
		return args -> {
			productService.save(new Product(1L, "TV Set", 300.00));
			productService.save(new Product(2L, "Game Console", 200.00));
			productService.save(new Product(3L, "Sofa", 100.00));
			productService.save(new Product(4L, "Icecream", 5.00));
			productService.save(new Product(5L, "Beer", 3.00));
			productService.save(new Product(6L, "Phone", 500.00));
			productService.save(new Product(7L, "Watch", 30.00));

			Set<Role> roles = new HashSet<>();
			var userRole = roleRepository.save(new Role(1L, "USER"));
			var adminRole = roleRepository.save(new Role(2L, "ADMIN"));
			roles.add(userRole);
			roles.add(adminRole);

			userDetailsRepository.save(new User(1L, "haldhar", "admin", "M", roles));

			roles = new HashSet<>();
			roles.add(userRole);

			userDetailsRepository.save(new User(2L, "user", "admin", "M", roles));

		};
	}

}

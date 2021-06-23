package com.order.orderdemo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;

import com.order.orderdemo.email.EmailService;
import com.order.orderdemo.email.model.ConfirmationEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.order.orderdemo.dao.entity.Order;
import com.order.orderdemo.dao.entity.OrderProduct;
import com.order.orderdemo.dao.entity.OrderStatus;
import com.order.orderdemo.service.OrderProductService;
import com.order.orderdemo.service.OrderService;
import com.order.orderdemo.service.ProductService;

@RestController
public class OrderController {

	@Autowired
	ProductService productService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderProductService orderProductService;

	@Autowired
	EmailService emailService;

	@PostMapping(path = "/order")
	public ResponseEntity<Order> create(@RequestBody OrderForm orderForm) {
		List<OrderProduct> formData = orderForm.getProductOrders();
		validateProductsExistence(formData);
		var order = new Order();
		order.setStatus(OrderStatus.PAID.name());
		order = orderService.create(order);

		List<OrderProduct> orderProducts = new ArrayList<>();
		for (OrderProduct orderProduct : formData) {
			orderProducts.add(orderProductService.create(
					new OrderProduct(order, productService.getProduct(orderProduct.getProduct().getId()), orderProduct.getQuantity())));
		}

		order.setOrderProducts(orderProducts);

		orderService.update(order);
		emailService.sendEmail(orderForm.getConfirmationEmail());

		var uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/orders/{id}")
				.buildAndExpand(order.getId()).toString();
		var headers = new HttpHeaders();
		headers.add("Location", uri);

		return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
	}

	private void validateProductsExistence(List<OrderProduct> orderProducts) {
		List<OrderProduct> list = orderProducts.stream()
				.filter(op -> Objects.isNull(productService.getProduct(op.getProduct().getId())))
				.collect(Collectors.toList());

		if (!CollectionUtils.isEmpty(list)) {
			throw new EntityExistsException("Product already Exists");
		}
	}

	public static class OrderForm {

		private List<OrderProduct> productOrders;

		private ConfirmationEmail confirmationEmail;

		public List<OrderProduct> getProductOrders() {
			return productOrders;
		}

		public void setProductOrders(List<OrderProduct> productOrders) {
			this.productOrders = productOrders;
		}

		public ConfirmationEmail getConfirmationEmail() {
			return confirmationEmail;
		}

		public void setConfirmationEmail(ConfirmationEmail confirmationEmail) {
			this.confirmationEmail = confirmationEmail;
		}
	}
}

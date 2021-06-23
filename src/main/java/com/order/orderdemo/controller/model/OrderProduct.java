package com.order.orderdemo.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.order.orderdemo.dao.entity.Product;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderProduct {

    @JsonProperty("product")
    private Product product;

    @JsonProperty("quantity")
    private Integer quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

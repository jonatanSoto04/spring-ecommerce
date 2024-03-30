package com.soto.ecommerce.springecommerce.service;

import com.soto.ecommerce.springecommerce.model.Order;

import java.util.List;

public interface IOrderService {
    List<Order> findAll();
    Order save(Order order);
}

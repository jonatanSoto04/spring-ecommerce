package com.soto.ecommerce.springecommerce.service;

import com.soto.ecommerce.springecommerce.model.Order;
import com.soto.ecommerce.springecommerce.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderSeriviceImpl implements  IOrderService{
    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
}

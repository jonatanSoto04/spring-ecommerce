package com.soto.ecommerce.springecommerce.service;

import com.soto.ecommerce.springecommerce.model.Order;
import com.soto.ecommerce.springecommerce.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderSeriviceImpl implements  IOrderService{
    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public String generarteNumberOrder(){
        int number = 0;
        String numberConcatenated = "";
        List<Order> orders = findAll();
        List<Integer> numbers = new ArrayList<Integer>();
        orders.stream().forEach(o -> numbers.add(Integer.parseInt(o.getNumber())));
        if(orders.isEmpty()){
            number = 1;
        }else {
            number = numbers.stream().max(Integer::compare).get();
            number++;
        }

        if(number<10){
            numberConcatenated="000000000"+ String.valueOf(number);
        }else if(number<100){
            numberConcatenated="00000000"+ String.valueOf(number);
        }else if(number<1000){
            numberConcatenated="0000000"+ String.valueOf(number);
        }else if(number<10000){
            numberConcatenated="000000"+ String.valueOf(number);
        }

        return numberConcatenated;
    }
}

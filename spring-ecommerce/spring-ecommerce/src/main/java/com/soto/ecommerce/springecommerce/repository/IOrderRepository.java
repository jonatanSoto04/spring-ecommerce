package com.soto.ecommerce.springecommerce.repository;

import com.soto.ecommerce.springecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer> {
}

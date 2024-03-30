package com.soto.ecommerce.springecommerce.repository;

import com.soto.ecommerce.springecommerce.model.DetailOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdetailOrderRepository extends JpaRepository<DetailOrder, Integer> {
}

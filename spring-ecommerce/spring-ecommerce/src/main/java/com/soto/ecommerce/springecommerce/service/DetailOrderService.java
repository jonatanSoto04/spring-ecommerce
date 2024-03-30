package com.soto.ecommerce.springecommerce.service;

import com.soto.ecommerce.springecommerce.model.DetailOrder;
import com.soto.ecommerce.springecommerce.repository.IdetailOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailOrderService implements IDetailOrderService{

    @Autowired
    private IdetailOrderRepository detailOrderRepository;
    @Override
    public DetailOrder save(DetailOrder detailOrder) {
        return detailOrderRepository.save(detailOrder);
    }
}

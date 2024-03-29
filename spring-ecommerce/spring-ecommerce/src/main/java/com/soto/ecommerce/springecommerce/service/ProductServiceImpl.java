package com.soto.ecommerce.springecommerce.service;

import com.soto.ecommerce.springecommerce.model.Product;
import com.soto.ecommerce.springecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productrepository;

    @Override
    public Product save(Product product) {
        return productrepository.save(product);
    }

    @Override
    public Optional<Product> get(Integer id) {
        return productrepository.findById(id);
    }

    @Override
    public void update(Product product) {
        productrepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        productrepository.deleteById(id);
    }
}

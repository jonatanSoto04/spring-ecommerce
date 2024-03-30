package com.soto.ecommerce.springecommerce.service;

import com.soto.ecommerce.springecommerce.model.Product;
import com.soto.ecommerce.springecommerce.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private IProductRepository productrepository;

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

    @Override
    public List<Product> findAll() {
        return productrepository.findAll();
    }
}

package com.soto.ecommerce.springecommerce.service;

import com.soto.ecommerce.springecommerce.model.Product;

import java.util.Optional;

public interface ProductService {
    public Product save(Product product);
    public Optional<Product> get(Integer id);
    public void update(Product product);
    public void delete(Integer id);
}

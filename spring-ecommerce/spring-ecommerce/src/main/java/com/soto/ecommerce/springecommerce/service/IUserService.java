package com.soto.ecommerce.springecommerce.service;

import com.soto.ecommerce.springecommerce.model.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findById(Integer id);
}

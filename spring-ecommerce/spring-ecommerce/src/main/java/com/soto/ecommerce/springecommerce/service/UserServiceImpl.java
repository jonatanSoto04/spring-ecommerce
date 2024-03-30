package com.soto.ecommerce.springecommerce.service;

import com.soto.ecommerce.springecommerce.model.User;
import com.soto.ecommerce.springecommerce.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private IUserRepository userRepository;
    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }
}

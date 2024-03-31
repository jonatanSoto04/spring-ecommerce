package com.soto.ecommerce.springecommerce.repository;

import com.soto.ecommerce.springecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository <User, Integer> {
    Optional<User> findByEmail(String email);
}

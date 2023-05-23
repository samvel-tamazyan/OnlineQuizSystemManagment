package com.example.onlinequizsystemmanagment.repository;

import com.example.onlinequizsystemmanagment.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    User findByActivationCode(String code);

    Optional<User> findByEmail(String email);
}
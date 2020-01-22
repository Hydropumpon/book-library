package com.example.library.repository;

import com.example.library.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Boolean existsByEmail(String email);

    Boolean existsByLogin(String login);

    Optional<Customer> findByLogin(String login);

    Optional<Customer> findByEmail(String email);

    Boolean existsByLoginOrEmail(String login, String email);
}

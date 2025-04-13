package com.pravin.security_login_register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pravin.security_login_register.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
  public Customer findByEmail(String email);
}
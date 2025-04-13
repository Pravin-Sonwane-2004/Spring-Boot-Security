package com.pravin.security_login_register.service;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pravin.security_login_register.entity.Customer;
import com.pravin.security_login_register.repository.CustomerRepository;

@Service
public class CustomerService implements UserDetailsService {

  private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

  @Autowired
  @Lazy
  private BCryptPasswordEncoder passwordEncoder;

  @Autowired
  private CustomerRepository repository;

  public boolean saveCustomer(Customer c) {
    String encodedPass = passwordEncoder.encode(c.getPass());
    c.setPass(encodedPass);
    Customer savedCustomer = repository.save(c);

    return savedCustomer.getId() != null;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    try {
      Customer customer = repository.findByEmail(email);
      if (customer == null) {
        throw new UsernameNotFoundException("User not found with email: " + email);
      }
      return new User(customer.getEmail(), customer.getPass(), Collections.emptyList());
    } catch (Exception e) {
      logger.error("Error loading user by username: {}", email, e);
      throw e;
    }
  }

}

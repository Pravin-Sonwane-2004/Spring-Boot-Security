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
import org.springframework.dao.IncorrectResultSizeDataAccessException;

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
    logger.info("Attempting to load user by email: {}", email); // Add logging
    try {
      Customer customer = repository.findByEmail(email);
      if (customer == null) {
        logger.warn("User not found with email: {}", email); // Add logging
        throw new UsernameNotFoundException("User not found with email: " + email);
      }
      logger.info("User found: {}", customer.getEmail()); // Add logging
      return new User(customer.getEmail(), customer.getPass(), Collections.emptyList());
    } catch (IncorrectResultSizeDataAccessException e) {
      logger.error("Multiple users found with email: {}", email, e);
      throw new UsernameNotFoundException("Multiple users found with email: " + email);
    } catch (Exception e) {
      logger.error("Error loading user by username: {}", email, e);
      throw e;
    }
  }

}

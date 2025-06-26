package com.pravin.security_login_register.controller;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pravin.security_login_register.entity.Customer;
import com.pravin.security_login_register.service.CustomerService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class CustomerController {

  private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  CustomerService service;

  @GetMapping("/welcome")
  public String securedWelcome() {
    return "this is welcome page";
  }

  @PostMapping("/register")
  public ResponseEntity<String> regsterCustomer(@RequestBody Customer c) {
    boolean status = service.saveCustomer(c);
    if (status) {
      return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
    return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody Customer c) {
    logger.info("Attempting login for email: {}", c.getEmail()); // Add logging
    try {
      UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(c.getEmail(), c.getPass());
      Authentication authentication = authenticationManager.authenticate(token);
      if (authentication.isAuthenticated()) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        logger.info("Login successful for email: {}", c.getEmail()); // Add logging
        return new ResponseEntity<>( HttpStatus.OK);
      }
    } catch (Exception e) {
      logger.error("Login failed for email: {}", c.getEmail(), e); // Add logging
      return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
    }
    return new ResponseEntity<>("Something Went Wrong", HttpStatus.BAD_REQUEST);
  }

}

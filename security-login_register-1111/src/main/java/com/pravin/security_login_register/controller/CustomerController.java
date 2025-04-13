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

@RestController
public class CustomerController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  CustomerService service;

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
    try {
      UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(c.getEmail(), c.getPass());
      Authentication authentication = authenticationManager.authenticate(token);
      if (authentication.isAuthenticated()) {
        return new ResponseEntity<>("Welcome, Login Is Successful", HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
    }
    return new ResponseEntity<>("Something Went Wrong", HttpStatus.BAD_REQUEST);
  }

}

package com.pravin.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pravin.client.dto.APIClient;

@RestController
public class ClientController {
  
  @Autowired
  APIClient apiClient;
   @GetMapping("/")
    public String fetchFromServer() {
      return apiClient.welcomeMessage();
    }

}

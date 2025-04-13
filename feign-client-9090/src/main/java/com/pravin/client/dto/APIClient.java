package com.pravin.client.dto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "security-project", url = "http://localhost:8080/")
public interface APIClient {

  @GetMapping("/")
  String welcomeMessage();
}

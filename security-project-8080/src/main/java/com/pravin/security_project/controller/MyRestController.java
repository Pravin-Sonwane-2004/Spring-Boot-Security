package com.pravin.security_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {
  
  @GetMapping("/contact")
  public String home() {
    return "9145649745";
  }
  
  @GetMapping("/api")
  public String getMethodName() {
    return "this is an api for remove security";
  }
  @GetMapping("/auth")
  public String auth() {
    return "this is authenticated";
  }
  
}

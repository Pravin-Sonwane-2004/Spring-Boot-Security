package com.pravin.spring_boot_oauth_app.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class DemoRestControler {
  

  @GetMapping("/")
  public String getmsg() {
    return "good bro you did it";
  }
  
}

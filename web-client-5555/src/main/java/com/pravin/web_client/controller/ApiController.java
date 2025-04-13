package com.pravin.web_client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pravin.web_client.service.ApiClientService;

import reactor.core.publisher.Mono;

@RestController
public class ApiController {

  private final ApiClientService apiClientService;

  public ApiController(ApiClientService apiClientService) {
    this.apiClientService = apiClientService;
  }

  @GetMapping("/")
  public Mono<String> callApi() {
    return apiClientService.getDataFromAPI(); // returns Mono without blocking
  }

  @GetMapping("/second")
  public Mono<String> call() {
    return apiClientService.getDataFromgreet();
  }
}
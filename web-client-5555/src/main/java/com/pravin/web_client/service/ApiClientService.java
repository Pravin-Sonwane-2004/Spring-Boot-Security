package com.pravin.web_client.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ApiClientService {

  private WebClient webClient ;

  public Mono<String> getDataFromAPI() {
    return webClient.get()
        .uri("/")
        .retrieve()
        .bodyToMono(String.class);
  }


  public Mono<String> getDataFromgreet() {
    return webClient.get()
        .uri("/api")
        .retrieve()
        .bodyToMono(String.class);
  }
}

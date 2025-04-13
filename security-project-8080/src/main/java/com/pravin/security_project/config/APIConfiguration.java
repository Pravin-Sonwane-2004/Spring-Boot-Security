package com.pravin.security_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
public class APIConfiguration {

  @Bean
  public SecurityFilterChain security(HttpSecurity http) throws Exception {

    http
        .authorizeHttpRequests(req -> req
            .requestMatchers("/contact").permitAll()
            .anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults()) // Enables Basic Auth
        .formLogin(Customizer.withDefaults()); // Enables Form Login

    return http.build();
  }
}

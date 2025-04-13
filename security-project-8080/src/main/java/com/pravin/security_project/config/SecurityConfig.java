package com.pravin.security_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  // @Bean
  // public PasswordEncoder passwordEncoder() {
  //   return new BCryptPasswordEncoder();
  // }

  // @Bean
  // public InMemoryUserDetailsManager userDetailsManager(PasswordEncoder encoder) {
  //   UserDetails user1 = User.withUsername("Raju")
  //       .password(encoder.encode("Raju"))
  //       .roles("USER")
  //       .build();

  //   UserDetails user2 = User.withUsername("Shyam")
  //       .password(encoder.encode("Shyam"))
  //       .roles("ADMIN")
  //       .build();

  //   UserDetails user3 = User.withUsername("Bablu")
  //       .password(encoder.encode("Bablu"))
  //       .roles("MANAGER")
  //       .build();

  //   return new InMemoryUserDetailsManager(user1, user2, user3);
  // }

  @Bean
  public SecurityFilterChain security(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(req -> req
            .requestMatchers("/contact").permitAll()
            .anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults())
        .formLogin(Customizer.withDefaults());

    return http.build();
  }
}

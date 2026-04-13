package com.example.kampay.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->auth
                        .requestMatchers("/api/users/register").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/users").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/users/*").permitAll()
                        .requestMatchers(HttpMethod.PATCH,"/api/users/*").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/api/users/*").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }

}

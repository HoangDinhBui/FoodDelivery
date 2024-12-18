package com.cybersoft.osahaneat.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CustomFilterSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Configure CSRF and CORS using the new lambda-based APIs
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable());

        // Define authorization rules
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login/**").permitAll() // Cho phép công khai
                .anyRequest().authenticated() // Cần xác thực với các endpoint khác
        );

        return http.build();
    }
}

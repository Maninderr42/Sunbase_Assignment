package com.example.SunbaseAssignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthenticationConfig {

    // Bean to configure the AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Bean to configure the AuthenticationProvider
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        // Creating a DaoAuthenticationProvider
        var authProvider = new DaoAuthenticationProvider();
        // Setting the UserDetailsService
        authProvider.setUserDetailsService(userDetailsService);
        // Setting the PasswordEncoder
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    // Bean to configure the PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Using BCryptPasswordEncoder for password encoding
        return new BCryptPasswordEncoder();
    }
}

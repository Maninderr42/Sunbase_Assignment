package com.example.SunbaseAssignment.config;

import com.example.SunbaseAssignment.Utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Configuration
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    // Method to filter each HTTP request
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Fetch token from request
        Optional<String> jwtTokenOptional = getTokenFromRequest(request);

        // Validate JWT Token using JwtUtils
        jwtTokenOptional.ifPresent(jwtToken -> {
            if (JwtUtils.validateToken(jwtToken)) {
                // Get username from JWT token
                var usernameOptional = JwtUtils.getUsernameFromToken(jwtToken);
                usernameOptional.ifPresent(username -> {
                    // Fetch user details with the help of username
                    var userDetails = userDetailsService.loadUserByUsername(username);
                    // Create authentication token
                    var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // Set authToken to security context
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                });
            }
        });

        // If token is null, pass request and response to the next filter
        filterChain.doFilter(request, response);
    }

    // Method to extract token from the request
    private Optional<String> getTokenFromRequest(HttpServletRequest request) {
        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer")) {
            return Optional.of(authHeader.substring(7));
        }
        return Optional.empty();
    }

    }
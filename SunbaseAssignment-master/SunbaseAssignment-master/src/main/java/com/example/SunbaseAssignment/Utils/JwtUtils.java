package com.example.SunbaseAssignment.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class JwtUtils {

    // Private constructor to prevent instantiation
    private JwtUtils() {}

    // Issuer name for the JWT token
    private static final String ISSUER = "Maninderdeep_Singh";

    // Secret key for signing the JWT token
    private static final SecretKey secretKey = Jwts.SIG.HS256.key().build();

    // Method to validate JWT token
    public static boolean validateToken(String jwtToken) {
        return parseToken(jwtToken).isPresent();
    }

    // Method to parse and verify JWT token
    private static Optional<Claims> parseToken(String jwtToken) {
        var jwtParser = Jwts.parser()
                .verifyWith(secretKey)
                .build();

        try {
            return Optional.of(jwtParser.parseSignedClaims(jwtToken)
                    .getPayload());
        } catch (JwtException | IllegalArgumentException e) {
            log.error("JWT Exception occurred");
        }

        return Optional.empty();
    }

    // Method to extract username from JWT token
    public static Optional<String> getUsernameFromToken(String jwtToken) {
        var claimsOptional = parseToken(jwtToken);

        if (claimsOptional.isPresent()) {
            return Optional.of(claimsOptional.get().getSubject());
        }
        return Optional.empty();
    }

    // Method to generate JWT token
    public static String generateToken(String username) {
        var currentDate = new Date();
        var expiration = DateUtils.addMinutes(currentDate, 10);
        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .issuer(ISSUER)
                .subject(username)
                .signWith(secretKey)
                .issuedAt(currentDate)
                .expiration(expiration)
                .compact();
    }
}

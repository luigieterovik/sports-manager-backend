package com.example.sportsmanager.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.sportsmanager.model.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(System.getenv("JWT_SECRET_KEY"));

            String token = JWT.create()
                    .withIssuer("sportsManager")
                    .withSubject(user.getEmail())
//                    .withExpiresAt(this.generateExpirationData())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while authenticating");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(System.getenv("JWT_SECRET_KEY"));
            return JWT.require(algorithm)
                    .withIssuer("sportsManager")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch(JWTVerificationException exception) {
            return null;
        }
    }

    private Instant generateExpirationData() {
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-03:00"));
    }
}

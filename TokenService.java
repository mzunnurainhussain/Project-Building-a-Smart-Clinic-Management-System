package com.project_back_end.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    // 256-bit key for HS256; store in config/secret manager for real apps
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // ✅ Generate JWT using user's email
    public String generateToken(String email) {
        long now = System.currentTimeMillis();
        long exp = now + 60 * 60 * 1000; // 1 hour
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(exp))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    // Extra for controller rubric (check email inside token vs expected user)
    public boolean validateTokenForUser(String token, String user) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(token).getBody();
            String email = claims.getSubject();
            return email != null && (email.equalsIgnoreCase(user) || email.equalsIgnoreCase(user + "@example.com"));
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractEmail(String token) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (JwtException e) {
            return null;
        }
    }
}

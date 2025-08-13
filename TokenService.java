package com.project_back_end.services;

import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Base64;

@Service
public class TokenService {

    // Demo-only token (NOT secure). Replace with real JWT later.
    public String generateToken(String username) {
        String payload = username + ":" + Instant.now().getEpochSecond();
        return Base64.getUrlEncoder().withoutPadding().encodeToString(payload.getBytes());
    }

    public boolean validateToken(String token) {
        try {
            String decoded = new String(Base64.getUrlDecoder().decode(token));
            return decoded.split(":").length == 2; // very naive check
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        try {
            String decoded = new String(Base64.getUrlDecoder().decode(token));
            return decoded.split(":")[0];
        } catch (Exception e) {
            return null;
        }
    }
}

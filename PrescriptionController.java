package com.project_back_end.controllers;

import com.project_back_end.models.Prescription;
import com.project_back_end.services.PrescriptionService;
import com.project_back_end.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService service;
    private final TokenService tokenService;

    public PrescriptionController(PrescriptionService service, TokenService tokenService) {
        this.service = service;
        this.tokenService = tokenService;
    }

    @PostMapping("/{token}")
    public ResponseEntity<?> createWithToken(@PathVariable String token,
                                             @RequestParam String user,
                                             @Valid @RequestBody Prescription p) {
        if (!tokenService.validateTokenForUser(token, user)) {
            return ResponseEntity.status(401).body("{\"error\":\"Invalid token or user\"}");
        }
        return ResponseEntity.ok(service.save(p));
    }
}

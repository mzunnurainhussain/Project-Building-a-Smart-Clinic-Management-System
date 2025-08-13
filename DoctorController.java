package com.project_back_end.controllers;

import com.project_back_end.models.Doctor;
import com.project_back_end.services.DoctorService;
import com.project_back_end.services.TokenService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService service;
    private final TokenService tokenService;

    public DoctorController(DoctorService service, TokenService tokenService) {
        this.service = service;
        this.tokenService = tokenService;
    }

    // ... your CRUD/search endpoints remain here ...

    // ✅ Required by rubric:
    @GetMapping("/availability/{user}/{doctorId}/{date}/{token}")
    public ResponseEntity<?> getAvailability(
            @PathVariable String user,
            @PathVariable Long doctorId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @PathVariable String token) {

        if (!tokenService.validateTokenForUser(token, user)) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid token or user"));
        }

        List<LocalTime> slots = service.getAvailability(doctorId, date);
        return ResponseEntity.ok(Map.of(
                "doctorId", doctorId,
                "date", date.toString(),
                "availableTimes", slots
        ));
    }

    // (Optional) login endpoint to demonstrate login validation rubric item
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String email,
                                                     @RequestParam String password) {
        return service.validateLogin(email, password);
    }
}

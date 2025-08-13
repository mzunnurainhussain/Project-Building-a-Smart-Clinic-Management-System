package com.project_back_end.controllers;

import com.project_back_end.models.Prescription;
import com.project_back_end.services.PrescriptionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService service;

    public PrescriptionController(PrescriptionService service) {
        this.service = service;
    }

    @GetMapping("/by-appointment/{appointmentId}")
    public List<Prescription> byAppointment(@PathVariable Long appointmentId) {
        return service.findByAppointmentId(appointmentId);
    }

    @PostMapping
    public Prescription create(@RequestBody Prescription p) {
        return service.save(p);
    }
}



// models/Prescription.java
package com.project_back_end.models;

import jakarta.persistence.*;

@Entity
@Table(name = "prescriptions")
public class Prescription {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @Column(nullable = false, length = 200)
    private String medicine;

    private String dosage;
    private String instructions;

    // getters/setters
    public Long getId() { return id; }
    public Appointment getAppointment() { return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment; }
    public String getMedicine() { return medicine; }
    public void setMedicine(String medicine) { this.medicine = medicine; }
    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
}




// services/PrescriptionService.java
package com.project_back_end.services;

import com.project_back_end.models.Prescription;
import com.project_back_end.repo.PrescriptionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PrescriptionService {
    private final PrescriptionRepository repo;
    public PrescriptionService(PrescriptionRepository repo) { this.repo = repo; }
    public Prescription save(Prescription p) { return repo.save(p); }
    public List<Prescription> findByAppointmentId(Long id) { return repo.findByAppointmentId(id); }
}





// repo/PrescriptionRepository.java
package com.project_back_end.repo;

import com.project_back_end.models.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByAppointmentId(Long appointmentId);
}

package com.project_back_end.services;

import com.project_back_end.models.Appointment;
import com.project_back_end.repo.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository repo;

    public AppointmentService(AppointmentRepository repo) {
        this.repo = repo;
    }

    public List<Appointment> findAll() { return repo.findAll(); }

    public Optional<Appointment> findById(Long id) { return repo.findById(id); }

    public Appointment save(Appointment appt) { return repo.save(appt); }

    public void delete(Long id) { repo.deleteById(id); }

    public List<Appointment> findByPatientId(Long patientId) {
        return repo.findByPatientIdOrderByAppointmentTimeAsc(patientId);
    }
}

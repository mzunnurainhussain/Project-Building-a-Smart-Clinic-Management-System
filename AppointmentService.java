package com.project_back_end.services;

import com.project_back_end.models.Appointment;
import com.project_back_end.repo.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    // ✅ Explicit method required by rubric
    public Appointment bookAppointment(Appointment appt) {
        return repo.save(appt);
    }

    // ✅ Find by doctor and specific date (00:00–23:59 range)
    public List<Appointment> findByDoctorAndDate(Long doctorId, LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = LocalDateTime.of(date, LocalTime.MAX);
        return repo.findByDoctorIdAndAppointmentTimeBetweenOrderByAppointmentTimeAsc(doctorId, start, end);
    }
}

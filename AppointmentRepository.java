package com.project_back_end.repo;

import com.project_back_end.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientIdOrderByAppointmentTimeAsc(Long patientId);
    List<Appointment> findByDoctorIdAndAppointmentTimeBetweenOrderByAppointmentTimeAsc(
            Long doctorId, LocalDateTime start, LocalDateTime end);
}

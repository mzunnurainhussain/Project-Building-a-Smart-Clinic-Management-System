package com.project_back_end.repo;

import com.project_back_end.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecialtyIgnoreCase(String specialty);
    List<Doctor> findBySpecialtyIgnoreCaseAndNameContainingIgnoreCase(String specialty, String name);
    Optional<Doctor> findByEmail(String email);
}

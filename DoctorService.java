package com.project_back_end.services;

import com.project_back_end.models.Doctor;
import com.project_back_end.repo.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository repo;

    public DoctorService(DoctorRepository repo) {
        this.repo = repo;
    }

    public List<Doctor> findAll() { return repo.findAll(); }

    public Optional<Doctor> findById(Long id) { return repo.findById(id); }

    public Doctor save(Doctor d) { return repo.save(d); }

    public Optional<Doctor> update(Long id, Doctor in) {
        return repo.findById(id).map(d -> {
            d.setName(in.getName());
            d.setSpecialty(in.getSpecialty());
            d.setEmail(in.getEmail());
            d.setPhone(in.getPhone());
            return repo.save(d);
        });
    }

    public void delete(Long id) { repo.deleteById(id); }

    public List<Doctor> search(String specialty, String name) {
        if (name == null || name.isBlank()) {
            return repo.findBySpecialtyIgnoreCase(specialty);
        }
        return repo.findBySpecialtyIgnoreCaseAndNameContainingIgnoreCase(specialty, name);
    }
}



package com.project_back_end.repo;

import com.project_back_end.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecialtyIgnoreCase(String specialty);
    List<Doctor> findBySpecialtyIgnoreCaseAndNameContainingIgnoreCase(String specialty, String name);
}

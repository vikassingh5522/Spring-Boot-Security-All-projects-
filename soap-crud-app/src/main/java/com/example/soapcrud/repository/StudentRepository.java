package com.example.soapcrud.repository;

import com.example.soapcrud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Custom query methods can be added here if needed
    Student findByEmail(String email);

    boolean existsByEmail(String email);
}
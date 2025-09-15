package com.example.soapcrud.service;

import com.example.soapcrud.entity.Student;
import com.example.soapcrud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setCourse(studentDetails.getCourse());

        return studentRepository.save(student);
    }

    public boolean deleteStudent(Long id) {
        try {
            studentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean existsByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }
}
package com.example.soapcrud.controller;

import com.example.soapcrud.entity.Student;
import com.example.soapcrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class StudentWebController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/students")
    public String viewStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "view-students";
    }

    @GetMapping("/students/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping("/students/add")
    public String addStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        try {
            if (studentService.existsByEmail(student.getEmail())) {
                redirectAttributes.addFlashAttribute("error", "Email already exists!");
                return "redirect:/students/add";
            }
            studentService.saveStudent(student);
            redirectAttributes.addFlashAttribute("success", "Student added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error adding student: " + e.getMessage());
        }
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            model.addAttribute("student", student.get());
            return "edit-student";
        } else {
            redirectAttributes.addFlashAttribute("error", "Student not found!");
            return "redirect:/students";
        }
    }

    @PostMapping("/students/update/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student student,
                                RedirectAttributes redirectAttributes) {
        try {
            studentService.updateStudent(id, student);
            redirectAttributes.addFlashAttribute("success", "Student updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating student: " + e.getMessage());
        }
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            boolean deleted = studentService.deleteStudent(id);
            if (deleted) {
                redirectAttributes.addFlashAttribute("success", "Student deleted successfully!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Failed to delete student!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting student: " + e.getMessage());
        }
        return "redirect:/students";
    }
}
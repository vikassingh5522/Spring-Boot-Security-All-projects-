package com.example.soapcrud.soap;

import com.example.soap.student.*;
import com.example.soapcrud.entity.Student;
import com.example.soapcrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.Optional;

@Endpoint
public class StudentEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/soap/student";

    @Autowired
    private StudentService studentService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentRequest")
    @ResponsePayload
    public GetStudentResponse getStudent(@RequestPayload GetStudentRequest request) {
        GetStudentResponse response = new GetStudentResponse();

        Optional<Student> studentOpt = studentService.getStudentById(request.getId());
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            com.example.soap.student.Student soapStudent = new com.example.soap.student.Student();
            soapStudent.setId(student.getId());
            soapStudent.setName(student.getName());
            soapStudent.setEmail(student.getEmail());
            soapStudent.setCourse(student.getCourse());
            response.setStudent(soapStudent);
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllStudentsRequest")
    @ResponsePayload
    public GetAllStudentsResponse getAllStudents(@RequestPayload GetAllStudentsRequest request) {
        GetAllStudentsResponse response = new GetAllStudentsResponse();

        List<Student> students = studentService.getAllStudents();
        for (Student student : students) {
            com.example.soap.student.Student soapStudent = new com.example.soap.student.Student();
            soapStudent.setId(student.getId());
            soapStudent.setName(student.getName());
            soapStudent.setEmail(student.getEmail());
            soapStudent.setCourse(student.getCourse());
            response.getStudents().add(soapStudent);
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addStudentRequest")
    @ResponsePayload
    public AddStudentResponse addStudent(@RequestPayload AddStudentRequest request) {
        AddStudentResponse response = new AddStudentResponse();

        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setCourse(request.getCourse());

        Student savedStudent = studentService.saveStudent(student);

        com.example.soap.student.Student soapStudent = new com.example.soap.student.Student();
        soapStudent.setId(savedStudent.getId());
        soapStudent.setName(savedStudent.getName());
        soapStudent.setEmail(savedStudent.getEmail());
        soapStudent.setCourse(savedStudent.getCourse());

        response.setStudent(soapStudent);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateStudentRequest")
    @ResponsePayload
    public UpdateStudentResponse updateStudent(@RequestPayload UpdateStudentRequest request) {
        UpdateStudentResponse response = new UpdateStudentResponse();

        Student studentDetails = new Student();
        studentDetails.setName(request.getName());
        studentDetails.setEmail(request.getEmail());
        studentDetails.setCourse(request.getCourse());

        Student updatedStudent = studentService.updateStudent(request.getId(), studentDetails);

        com.example.soap.student.Student soapStudent = new com.example.soap.student.Student();
        soapStudent.setId(updatedStudent.getId());
        soapStudent.setName(updatedStudent.getName());
        soapStudent.setEmail(updatedStudent.getEmail());
        soapStudent.setCourse(updatedStudent.getCourse());

        response.setStudent(soapStudent);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteStudentRequest")
    @ResponsePayload
    public DeleteStudentResponse deleteStudent(@RequestPayload DeleteStudentRequest request) {
        DeleteStudentResponse response = new DeleteStudentResponse();

        boolean success = studentService.deleteStudent(request.getId());
        response.setSuccess(success);
        response.setMessage(success ? "Student deleted successfully" : "Failed to delete student");

        return response;
    }
}
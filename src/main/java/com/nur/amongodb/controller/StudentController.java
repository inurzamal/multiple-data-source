package com.nur.amongodb.controller;

import com.nur.amongodb.dto.StudentDTO;
import com.nur.amongodb.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("api/v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO dto = studentService.saveStudent(studentDTO);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> fetchAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

}

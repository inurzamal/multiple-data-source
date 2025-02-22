package com.nur.amongodb.service;

import com.nur.amongodb.dto.StudentDTO;
import com.nur.amongodb.entity.Student;
import com.nur.amongodb.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public StudentService(StudentRepository studentRepository, MongoTemplate mongoTemplate) {
        this.studentRepository = studentRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student student = new Student(studentDTO.getRollNo(), studentDTO.getName(), studentDTO.getCity());
        Student entity = studentRepository.save(student);
        return convertToStudentDTO(entity);
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().map(this::convertToStudentDTO).toList();
    }

    private StudentDTO convertToStudentDTO(Student entity) {
        StudentDTO dto = new StudentDTO();
        dto.setRollNo(entity.getRollNo());
        dto.setName(entity.getName());
        dto.setCity(entity.getCity());
        return dto;
    }


    public List<StudentDTO> findStudentsByCity(String city) {
        Query query = new Query();
        query.addCriteria(Criteria.where("city").is(city));
        List<Student> students = mongoTemplate.find(query, Student.class);
        return students.stream().map(this::convertToStudentDTO).toList();
    }

}

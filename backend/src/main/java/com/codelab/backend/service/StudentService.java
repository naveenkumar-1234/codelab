package com.codelab.backend.service;

import com.codelab.backend.model.Student;
import com.codelab.backend.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;



    public Student addStudent(Student student){
        return studentRepository.save(student);
    }
    public List<Student> getAllStudents(){
        return  studentRepository.findAll();
    }

    @Cacheable(value = "students", key = "#email")
    public Student getStudentByEmail(String email){
        System.out.println("in service");
        return Student.builder().build();
//        return studentRepository
//        return studentRepository.findByEmail(email);
    }
}
